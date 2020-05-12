package com.decagonhq.hireday.services;

import com.decagonhq.hireday.entities.Decadev;
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
        if(!identificationRepository.existsIdentificationByDecaId(decadev.getDecaId())) {
            throw new DecadevIdException("Invalid Decadev ID number");
        }
        try {
            return decadevRepository.save(decadev);

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
