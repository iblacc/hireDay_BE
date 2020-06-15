package com.decagonhq.hireday.controllers;

import com.decagonhq.hireday.dto.EmployerRegisterDTO;
import com.decagonhq.hireday.entities.Employer;
import com.decagonhq.hireday.services.EmployerService;
import com.decagonhq.hireday.services.RequestBodyValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

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

    @PostMapping("/register")
    public ResponseEntity<?> registerEmployer(@Valid @RequestBody EmployerRegisterDTO employerRegisterDTO, BindingResult result) {

        ResponseEntity<?> errors = requestBodyValidationService.requestBodyValidation(result);
        if(errors != null) return errors;

        Employer employer = employerService.registerEmployer(employerRegisterDTO);
        return new ResponseEntity<>(employer, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('employer:read')")
    public ResponseEntity<?> getEmployer(@RequestParam("email") String email) {
        Employer employer = employerService.getEmployerByEmail(email);
        return new ResponseEntity<>(employer, HttpStatus.OK);
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Iterable<?>> getAllEmployers() {
        Iterable<Employer> employers = employerService.getAllEmployers();
        return new ResponseEntity<>(employers, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    public ResponseEntity<?> updateEmployer(@RequestParam("id") long id, @Valid @RequestBody Employer employer, Principal principal, BindingResult result) {

        ResponseEntity<?> errors = requestBodyValidationService.requestBodyValidation(result);
        if(errors != null) return errors;

        Employer employer1 = employerService.updateEmployer(id, employer, principal.getName());
        return new ResponseEntity<>(employer1, HttpStatus.CREATED);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteEmployer(@RequestParam("id") long id) {
        employerService.deleteEmployer(id);
        return new ResponseEntity<>("Employer with ID '" + id + "' was removed successfully", HttpStatus.OK);
    }
}
