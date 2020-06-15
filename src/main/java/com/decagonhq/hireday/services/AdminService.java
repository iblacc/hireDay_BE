package com.decagonhq.hireday.services;

import com.decagonhq.hireday.dto.AdminRegisterDTO;
import com.decagonhq.hireday.entities.Admin;
import com.decagonhq.hireday.entities.UserIdentity;
import com.decagonhq.hireday.exceptions.AdminNotFoundException;
import com.decagonhq.hireday.exceptions.UserPasswordException;
import com.decagonhq.hireday.exceptions.UserAlreadyExistException;
import com.decagonhq.hireday.repositories.AdminRepository;
import com.decagonhq.hireday.repositories.UserIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private AdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;
    private UserIdentityRepository userIdentityRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder, UserIdentityRepository userIdentityRepository) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.userIdentityRepository = userIdentityRepository;
    }

    public Admin registerAdmin(AdminRegisterDTO adminRegisterDTO) {

        if(!adminRegisterDTO.comparePasswords()) {
            throw new UserPasswordException("Passwords do not match");
        }

        boolean isRegistered = userIdentityRepository.existsByEmail(adminRegisterDTO.getEmail());
        if(isRegistered) throw new UserAlreadyExistException("User with email '" + adminRegisterDTO.getEmail() + "' already exists.");

        Admin admin = new Admin(
                adminRegisterDTO.getFirstName(),
                adminRegisterDTO.getLastName(),
                adminRegisterDTO.getEmail(),
                passwordEncoder.encode(adminRegisterDTO.getPassword())
        );

        Admin registeredAdmin = adminRepository.save(admin);
        userIdentityRepository.save(new UserIdentity(adminRegisterDTO.getEmail()));
        return registeredAdmin;
    }

    public Admin getAdminByEmail(String email) {
        Optional<Admin> admin = adminRepository.findByEmail(email);

        if(admin.isEmpty()) {
            throw new AdminNotFoundException("Admin with email '" + email + "' could not be found");
        }

        return admin.get();
    }

    public Iterable<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin updateAdmin(long id, Admin admin, String principalEmail) {
        Optional<Admin> admin1 = adminRepository.findById(id);

        if(admin1.isEmpty()) {
            throw new AdminNotFoundException("Admin with ID '" + id + "' does not exit");
        }

        Admin foundAdmin = admin1.get();
        if(id == admin.getId() && foundAdmin.getEmail().equals(principalEmail)) {

            Optional<UserIdentity> identity = userIdentityRepository.findByEmail(admin.getEmail());
            if(identity.isPresent() && !principalEmail.equals(identity.get().getEmail())) {
                throw new UserAlreadyExistException("Admin with email '" + admin.getEmail() + "' already exists");
            }

            return adminRepository.save(admin);
        }

        throw new AdminNotFoundException("You cannot update another admin");
    }
}
