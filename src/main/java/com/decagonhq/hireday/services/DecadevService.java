package com.decagonhq.hireday.services;

import com.decagonhq.hireday.entities.Decadev;
import com.decagonhq.hireday.exceptions.DecadevIdException;
import com.decagonhq.hireday.exceptions.DecadevNotFoundException;
import com.decagonhq.hireday.repositories.DecadevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DecadevService {

    private DecadevRepository decadevRepository;

    @Autowired
    public DecadevService(DecadevRepository decadevRepository) {
        this.decadevRepository = decadevRepository;
    }

    public Decadev createOrUpdateDecadev(Decadev decadev) {
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

    public Iterable<Decadev> getAllDecadevs() {
        return decadevRepository.findAll();
    }

    public void deleteDecadev(String decaId) {
        try {
            decadevRepository.delete(getDecadev(decaId));
        } catch (Exception ex) {
            throw new DecadevNotFoundException("Decadev with ID number '" + decaId + "' could not be found");
        }
    }
}
