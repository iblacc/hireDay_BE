package com.decagonhq.hireday.services;

import com.decagonhq.hireday.entities.Admin;
import com.decagonhq.hireday.entities.Decadev;
import com.decagonhq.hireday.entities.Employer;
import com.decagonhq.hireday.repositories.AdminRepository;
import com.decagonhq.hireday.repositories.DecadevRepository;
import com.decagonhq.hireday.repositories.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final DecadevRepository decadevRepository;
    private final EmployerRepository employerRepository;


    @Autowired
    public UserService(AdminRepository adminRepository, DecadevRepository decadevRepository, EmployerRepository employerRepository) {
        this.adminRepository = adminRepository;
        this.decadevRepository = decadevRepository;
        this.employerRepository = employerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Decadev> decadev = decadevRepository.findByEmail(username);
        if(decadev.isPresent()) return decadev.get();
        Optional<Employer> employer = employerRepository.findByEmail(username);
        if(employer.isPresent()) return employer.get();
        Optional<Admin> admin = adminRepository.findByEmail(username);
        if(admin.isPresent()) return admin.get();

        throw new UsernameNotFoundException(String.format("Username %s not found", username));
    }
}
