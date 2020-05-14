package com.decagonhq.hireday.services;

import com.decagonhq.hireday.entities.Employer;
import com.decagonhq.hireday.exceptions.EmployerNotFoundException;
import com.decagonhq.hireday.exceptions.OrganizationException;
import com.decagonhq.hireday.repositories.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployerService {

    private EmployerRepository employerRepository;

    @Autowired
    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    public Employer createEmployer(Employer employer) {
        try {
            return employerRepository.save(employer);
        } catch (Exception ex) {
            throw new OrganizationException("Organization name and/or email already exists");
        }
    }

    public Employer getEmployer(long id) {
        Optional<Employer> employer = employerRepository.findById(id);

        if(employer.isEmpty()) {
            throw new EmployerNotFoundException("Employer with ID '" + id + "' could not be found");
        }

        return employer.get();
    }

    public Iterable<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }

    public Employer updateEmployer(long id, Employer employer) {
        Optional<Employer> employer1 = employerRepository.findById(id);

        if(employer1.isEmpty()) {
            throw new EmployerNotFoundException("Employer with ID '" + id + "' could not be found");
        }

        Employer foundEmployer = employer1.get();
        if(foundEmployer.getCompanyName().equals(employer.getCompanyName()) && foundEmployer.getEmail().equals(employer.getEmail())) {
            return employerRepository.save(employer);
        }

        throw new OrganizationException("Organization name and/or email not updatable");
    }

    public void deleteEmployer(long id) {
        try {
            employerRepository.delete(getEmployer(id));
        } catch (Exception ex) {
            throw new EmployerNotFoundException("Employer with ID '" + id + "' could not be found");
        }
    }
}
