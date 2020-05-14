package com.decagonhq.hireday.controllers;

import com.decagonhq.hireday.entities.Decadev;
import com.decagonhq.hireday.entities.Employer;
import com.decagonhq.hireday.services.EmployerService;
import com.decagonhq.hireday.services.RequestBodyValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/employers")
@CrossOrigin
public class EmployerController {

    private EmployerService employerService;
    private RequestBodyValidationService requestBodyValidationService;

    @Autowired
    public EmployerController(EmployerService employerService, RequestBodyValidationService requestBodyValidationService) {
        this.employerService = employerService;
        this.requestBodyValidationService = requestBodyValidationService;
    }

    @PostMapping
    public ResponseEntity<?> createEmployer(@Valid @RequestBody Employer employer, BindingResult result) {

        ResponseEntity<?> errors = requestBodyValidationService.requestBodyValidation(result);
        if(errors != null) return errors;

        Employer employer1 = employerService.createEmployer(employer);
        return new ResponseEntity<>(employer1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployer(@PathVariable("id") long id) {
        Employer employer = employerService.getEmployer(id);
        return new ResponseEntity<>(employer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<?>> getAllEmployers() {
        Iterable<Employer> employers = employerService.getAllEmployers();
        return new ResponseEntity<>(employers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployer(@PathVariable("id") long id, @Valid @RequestBody Employer employer, BindingResult result) {

        ResponseEntity<?> errors = requestBodyValidationService.requestBodyValidation(result);
        if(errors != null) return errors;

        Employer employer1 = employerService.updateEmployer(id, employer);
        return new ResponseEntity<>(employer1, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployer(@PathVariable("id") long id) {
        employerService.deleteEmployer(id);
        return new ResponseEntity<>("Employer with ID '" + id + "' was deleted", HttpStatus.OK);
    }
}