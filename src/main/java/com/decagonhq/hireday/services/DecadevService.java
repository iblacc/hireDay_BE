package com.decagonhq.hireday.services;

import com.decagonhq.hireday.entities.Decadev;
import com.decagonhq.hireday.entities.Identification;
import com.decagonhq.hireday.exceptions.DecadevIdException;
import com.decagonhq.hireday.exceptions.DecadevNotFoundException;
import com.decagonhq.hireday.repositories.DecadevRepository;
import com.decagonhq.hireday.repositories.IdentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DecadevService {

    private DecadevRepository decadevRepository;
    private IdentificationRepository identificationRepository;

    @Autowired
    public DecadevService(DecadevRepository decadevRepository, IdentificationRepository identificationRepository) {
        this.decadevRepository = decadevRepository;
        this.identificationRepository = identificationRepository;
    }

    public Decadev createDecadev(Decadev decadev) {
        Optional<Identification> identification = identificationRepository.findByDecaId(decadev.getDecaId());
        if(identification.isEmpty()) {
            throw new DecadevIdException("Invalid Decadev ID number '" + decadev.getDecaId() + "'");
        }

        if(!identification.get().isAccountCreated()) {
            throw new DecadevIdException("Decadev account with ID '" + decadev.getDecaId() + "' has not been created")
        }

        try {
            Decadev saveDecadev = decadevRepository.save(decadev);
            Identification foundId = identification.get();
            foundId.setProfileCreated(true);
            identificationRepository.save(foundId);
            return saveDecadev;
        } catch (Exception ex) {
            throw new DecadevIdException("Decadev already exists");
        }
    }

    public Decadev getDecadev(String decaId) {
        Optional<Decadev> decadev = decadevRepository.findByDecaId(decaId);

        if(decadev.isPresent()) {
            return decadev.get();
        }

        throw new DecadevNotFoundException("Decadev with ID number '" + decaId + "' could not be found");
    }

    public Iterable<Decadev> getDecadevsByStack(String stack) {
        return decadevRepository.findAllByStack(stack);
    }

    public Iterable<Decadev> getAllDecadevs() {
        return decadevRepository.findAll();
    }

    public Decadev updateDecadev(String decaId, Decadev decadev) {

        Optional<Decadev> decadev1 = decadevRepository.findByDecaId(decaId);
        if(decadev1.isEmpty()) {
            throw new DecadevNotFoundException("Decadev could not be found");
        }

        Decadev foundDecadev = decadev1.get();

        if(foundDecadev.getEmail().equals(decadev.getEmail())) {
            return decadevRepository.save(decadev);
        }

        throw new DecadevIdException("Decadev ID and/or email did not match");
    }

    public void deleteDecadev(String decaId) {
        try {
            decadevRepository.delete(getDecadev(decaId));
        } catch (Exception ex) {
            throw new DecadevNotFoundException("Decadev with ID number '" + decaId + "' could not be found");
        }
    }
}
