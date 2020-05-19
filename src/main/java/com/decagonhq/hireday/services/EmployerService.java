package com.decagonhq.hireday.services;

import com.decagonhq.hireday.entities.Employer;
import com.decagonhq.hireday.exceptions.EmployerNotFoundException;
import com.decagonhq.hireday.exceptions.CompanyException;
import com.decagonhq.hireday.repositories.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployerService {

    private EmployerRepository employerRepository;
    private EmailService emailService;

    @Autowired
    public EmployerService(EmployerRepository employerRepository, EmailService emailService) {
        this.employerRepository = employerRepository;
        this.emailService = emailService;
    }

    public Employer createEmployer(Employer employer) {
        try {
            Employer saveEmployer = employerRepository.save(employer);
            emailService.send(employer.getFirstName(), employer.getLastName(), employer.getEmail());
            return saveEmployer;
        } catch (Exception ex) {
            throw new CompanyException("Company email already exists");
        }
    }

    public Employer getEmployerByEmail(String email) {
        Optional<Employer> employer = employerRepository.findByEmail(email);

        if(employer.isEmpty()) {
            throw new EmployerNotFoundException("Employer with email '" + email + "' could not be found");
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

        throw new CompanyException("Organization name and/or email not updatable");
    }

    public void deleteEmployer(long id) {

        Optional<Employer> employer = employerRepository.findById(id);
        if(employer.isEmpty()) {
            throw new EmployerNotFoundException("Employer with ID '" + id + "' could not be found");
        }
        try {
            employerRepository.delete(employer.get());
        } catch (Exception ex) {
            throw new EmployerNotFoundException("Employer with ID '" + id + "' could not be found");
        }
    }
}
