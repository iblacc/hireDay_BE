package com.decagonhq.hireday.controllers;

import com.decagonhq.hireday.dto.DecadevRegisterDTO;
import com.decagonhq.hireday.entities.Decadev;
import com.decagonhq.hireday.services.DecadevService;
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
@RequestMapping("api/v1/decadevs")
@CrossOrigin
public class DecadevController {

    private DecadevService decadevService;
    private RequestBodyValidationService requestBodyValidationService;

    @Autowired
    public DecadevController(DecadevService decadevService, RequestBodyValidationService requestBodyValidationService) {
        this.decadevService = decadevService;
        this.requestBodyValidationService = requestBodyValidationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerDecadev(@Valid @RequestBody DecadevRegisterDTO decadevRegisterDTO, BindingResult result) {

        ResponseEntity<?> errors = requestBodyValidationService.requestBodyValidation(result);
        if(errors != null) return errors;

        Decadev decadev = decadevService.registerDecadev(decadevRegisterDTO);
        return new ResponseEntity<>(decadev, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('decadev:read')")
    public ResponseEntity<?> getDecadev(@RequestParam("email") String email) {
        Decadev decadev = decadevService.getDecadev(email);
        return new ResponseEntity<>(decadev, HttpStatus.OK);
    }

    @GetMapping("/stacks")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_EMPLOYER')")
    public ResponseEntity<Iterable<?>> getDecadevsByStack(@RequestParam("stack") String stack) {
        Iterable<Decadev> decadevs = decadevService.getDecadevsByStack(stack);
        return new ResponseEntity<>(decadevs, HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_EMPLOYER')")
    public ResponseEntity<Iterable<?>> getAllDecadevs() {
        Iterable<Decadev> decadevs = decadevService.getAllDecadevs();
        return new ResponseEntity<>(decadevs, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_DECADEV')")
    public ResponseEntity<?> updateDecadev(@RequestParam("id") long id, @Valid @RequestBody Decadev decadev, Principal principal, BindingResult result) {

        ResponseEntity<?> errors = requestBodyValidationService.requestBodyValidation(result);
        if(errors != null) return errors;

        Decadev decadev1 = decadevService.updateDecadev(id, decadev, principal.getName());
        return new ResponseEntity<>(decadev1, HttpStatus.CREATED);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteDecadev(@RequestParam("id") long id) {
        decadevService.deleteDecadev(id);
        return new ResponseEntity<>("Decadev with ID '" + id + "' was removed successfully", HttpStatus.OK);
    }
}
