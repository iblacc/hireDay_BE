package com.decagonhq.hireday.services;

import com.decagonhq.hireday.dto.LoginDTO;
import com.decagonhq.hireday.dto.RegisterDTO;
import com.decagonhq.hireday.entities.Identification;
import com.decagonhq.hireday.exceptions.DecadevIdException;
import com.decagonhq.hireday.exceptions.DecadevNotFoundException;
import com.decagonhq.hireday.exceptions.DecadevPasswordException;
import com.decagonhq.hireday.repositories.IdentificationRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdentificationService {

    private IdentificationRepository identificationRepository;

    @Autowired
    public IdentificationService(IdentificationRepository identificationRepository) {
        this.identificationRepository = identificationRepository;
    }

    public Identification login(LoginDTO loginDTO) {

        Identification foundId = verifyDecaId(loginDTO.getDecaId());

        if(foundId.getPassword() == null) {
            throw new DecadevIdException("Decadev account with ID '" + loginDTO.getDecaId() + "' has not been created");
        }
        boolean isValid = verifyHash(loginDTO.getPassword(), foundId.getPassword());

        if(!isValid) {
            throw new DecadevPasswordException("Invalid password");
        }

        return foundId;
    }

    public Identification register(RegisterDTO registerDTO) {

        Identification foundId = verifyDecaId(registerDTO.getDecaId());
        if(!registerDTO.comparePasswords()) {
            throw new DecadevPasswordException("Passwords do not match");
        }

        String hashPassword = hash(registerDTO.getPassword());
        foundId.setPassword(hashPassword);
        foundId.setAccountCreated(true);
        identificationRepository.save(foundId);
        return foundId;
    }

    public Identification createIdentification(Identification identification) {
        try {
            return identificationRepository.save(identification);
        } catch (Exception ex) {
            throw new DecadevIdException("Identification already exists");
        }
    }

    public Identification getIdentificationByDecaId(String decaId) {
        Optional<Identification> identification = identificationRepository.findByDecaId(decaId);

        if(identification.isPresent()) {
            return identification.get();
        }

        throw new DecadevNotFoundException("Identification with decaId '" + decaId + "' could not be found");
    }

    public Iterable<Identification> getAllIdentification() {
        return identificationRepository.findAll();
    }

    public Identification updateIdentification(long id, Identification identification) {
        Optional<Identification> identification1 = identificationRepository.findById(id);

        if(identification1.isEmpty()) {
            throw new DecadevNotFoundException("Identification with ID '" + id + "' could not be found");
        }

        return identificationRepository.save(identification);
    }

    public void deleteIdentification(Long id) {
        try {
            identificationRepository.delete(getIdentification(id));
        } catch (Exception ex) {
            throw new DecadevNotFoundException("Identification with ID '" + id + "' could not be found");
        }
    }

    private Identification getIdentification(Long id) {
        Optional<Identification> identification = identificationRepository.findById(id);

        if(identification.isPresent()) {
            return identification.get();
        }

        throw new DecadevNotFoundException("Identification with ID '" + id + "' could not be found");
    }

    private Identification verifyDecaId(String decaId) {
        Optional<Identification> identification = identificationRepository.findByDecaId(decaId);

        if(identification.isEmpty()) {
            throw new DecadevIdException("Invalid Decadev ID");
        }

        return identification.get();
    }

    private String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(11));
    }

    private boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
