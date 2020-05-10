package com.decagonhq.hireday.services;

import com.decagonhq.hireday.entities.Decadev;
import com.decagonhq.hireday.entities.Identification;
import com.decagonhq.hireday.exceptions.DecadevIdException;
import com.decagonhq.hireday.exceptions.DecadevNotFoundException;
import com.decagonhq.hireday.repositories.IdentificationRepository;
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

    public Identification createOrUpdateIdentification(Identification identification) {
        try {
            return identificationRepository.save(identification);
        } catch (Exception ex) {
            throw new DecadevIdException("Identification already exists");
        }
    }

    public Identification getIdentification(Long id) {
        Optional<Identification> identification = identificationRepository.findById(id);

        if(identification.isPresent()) {
            return identification.get();
        }

        throw new DecadevNotFoundException("Identification with ID '" + id + "' could not be found");
    }

    public Iterable<Identification> getAllIdentification() {
        return identificationRepository.findAll();
    }

    public void deleteIdentification(Long id) {
        try {
            identificationRepository.delete(getIdentification(id));
        } catch (Exception ex) {
            throw new DecadevNotFoundException("Identification with ID '" + id + "' could not be found");
        }
    }
}
