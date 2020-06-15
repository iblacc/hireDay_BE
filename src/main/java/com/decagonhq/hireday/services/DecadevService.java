package com.decagonhq.hireday.services;

import com.decagonhq.hireday.dto.DecadevRegisterDTO;
import com.decagonhq.hireday.entities.Decadev;
import com.decagonhq.hireday.entities.UserIdentity;
import com.decagonhq.hireday.exceptions.*;
import com.decagonhq.hireday.repositories.DecadevRepository;
import com.decagonhq.hireday.repositories.UserIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DecadevService {

    private DecadevRepository decadevRepository;
    private PasswordEncoder passwordEncoder;
    private UserIdentityRepository userIdentityRepository;

    @Autowired
    public DecadevService(DecadevRepository decadevRepository, PasswordEncoder passwordEncoder, UserIdentityRepository userIdentityRepository) {
        this.decadevRepository = decadevRepository;
        this.passwordEncoder = passwordEncoder;
        this.userIdentityRepository = userIdentityRepository;
    }

    public Decadev registerDecadev(DecadevRegisterDTO registerDTO) {

        if(!registerDTO.comparePasswords()) {
            throw new UserPasswordException("Passwords do not match");
        }

        boolean isRegistered = userIdentityRepository.existsByEmail(registerDTO.getEmail());
        if(isRegistered) throw new UserAlreadyExistException("User with email '" + registerDTO.getEmail() + "' already exists.");

        Decadev decadev = new Decadev(
                registerDTO.getFirstName(),
                registerDTO.getLastName(),
                registerDTO.getDecaId(),
                registerDTO.getEmail(),
                registerDTO.getStack(),
                passwordEncoder.encode(registerDTO.getPassword())
        );

        try {
            Decadev registeredDecadev = decadevRepository.save(decadev);
            userIdentityRepository.save(new UserIdentity(registerDTO.getEmail()));
            return registeredDecadev;
        } catch (Exception ex) {
            throw new UserAlreadyExistException("User with ID '" + registerDTO.getDecaId() +  "' already exists.");
        }
    }

    public Decadev getDecadev(String email) {
        Optional<Decadev> decadev = decadevRepository.findByEmail(email);

        if(decadev.isPresent()) {
            return decadev.get();
        }

        throw new DecadevNotFoundException("Decadev with email '" + email + "' could not be found");
    }

    public Iterable<Decadev> getDecadevsByStack(String stack) {
        return decadevRepository.findAllByStack(stack);
    }

    public Iterable<Decadev> getAllDecadevs() {
        return decadevRepository.findAll();
    }

    public Decadev updateDecadev(long id, Decadev decadev, String principalEmail) {
        Optional<Decadev> decadev1 = decadevRepository.findById(id);
        if(decadev1.isEmpty()) {
            throw new DecadevNotFoundException("Decadev with ID '" + id + "' does not exit");
        }

        Decadev foundDecadev = decadev1.get();
        if(id == decadev.getId() && foundDecadev.getEmail().equals(principalEmail)) {

            Optional<UserIdentity> identity = userIdentityRepository.findByEmail(decadev.getEmail());
            if(identity.isPresent() && !principalEmail.equals(identity.get().getEmail())) {
                throw new UserAlreadyExistException("Decadev with email '" + decadev.getEmail() + "' already exists");
            }

            try {
                return decadevRepository.save(decadev);
            } catch (Exception ex) {
                throw new UserAlreadyExistException("Decadev with ID '" + decadev.getDecaId() + "' already exists");
            }
        }

        throw new DecadevNotFoundException("You cannot update another decadev");
    }

    public void deleteDecadev(long id) {

        Optional<Decadev> decadev = decadevRepository.findById(id);
        if(decadev.isEmpty()) {
            throw new DecadevNotFoundException("Decadev with ID '" + id + "' could not be found");
        }

        decadevRepository.delete(decadev.get());

        Optional<UserIdentity> identity = userIdentityRepository.findByEmail(decadev.get().getEmail());
        userIdentityRepository.delete(identity.get());
    }
}
