package com.decagonhq.hireday.controllers;

import com.decagonhq.hireday.dto.LoginDTO;
import com.decagonhq.hireday.dto.RegisterDTO;
import com.decagonhq.hireday.entities.Decadev;
import com.decagonhq.hireday.entities.Identification;
import com.decagonhq.hireday.services.DecadevService;
import com.decagonhq.hireday.services.IdentificationService;
import com.decagonhq.hireday.services.RequestBodyValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/decadevs")
@CrossOrigin
public class DecadevController {

    private DecadevService decadevService;
    private IdentificationService identificationService;
    private RequestBodyValidationService requestBodyValidationService;

    @Autowired
    public DecadevController(DecadevService decadevService, IdentificationService identificationService, RequestBodyValidationService requestBodyValidationService) {
        this.decadevService = decadevService;
        this.identificationService = identificationService;
        this.requestBodyValidationService = requestBodyValidationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginDecadev(@Valid @RequestBody LoginDTO loginDTO, BindingResult result) {

        ResponseEntity<?> errors = requestBodyValidationService.requestBodyValidation(result);
        if(errors != null) return errors;

        Identification identification = identificationService.login(loginDTO);
        return new ResponseEntity<>(identification, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerDecadev(@Valid @RequestBody RegisterDTO registerDTO, BindingResult result) {

        ResponseEntity<?> errors = requestBodyValidationService.requestBodyValidation(result);
        if(errors != null) return errors;

        Identification identification = identificationService.register(registerDTO);
        return new ResponseEntity<>(identification, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<?> createDecadev(@Valid @RequestBody Decadev decadev, BindingResult result) {

        ResponseEntity<?> errors = requestBodyValidationService.requestBodyValidation(result);
        if(errors != null) return errors;

        Decadev decadev1 = decadevService.createDecadev(decadev);
        return new ResponseEntity<>(decadev1, HttpStatus.CREATED);
    }

    @GetMapping("/{decaId}")
    public ResponseEntity<?> getDecadev(@PathVariable("decaId") String decaId) {
        Decadev decadev = decadevService.getDecadev(decaId);
        return new ResponseEntity<>(decadev, HttpStatus.OK);
    }

    @GetMapping("/stacks/{stack}")
    public ResponseEntity<Iterable<?>> getDecadevsByStack(@PathVariable("stack") String stack) {
        Iterable<Decadev> decadevs = decadevService.getDecadevsByStack(stack);
        return new ResponseEntity<>(decadevs, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<?>> getAllDecadevs() {
        Iterable<Decadev> decadevs = decadevService.getAllDecadevs();
        return new ResponseEntity<>(decadevs, HttpStatus.OK);
    }

    @PutMapping("{stackId}")
    public ResponseEntity<?> updateDecadev(@PathVariable("stackId") String decaId, @Valid @RequestBody Decadev decadev, BindingResult result) {

        ResponseEntity<?> errors = requestBodyValidationService.requestBodyValidation(result);
        if(errors != null) return errors;

        Decadev decadev1 = decadevService.updateDecadev(decaId ,decadev);
        return new ResponseEntity<>(decadev1, HttpStatus.CREATED);
    }

    @DeleteMapping("{decaId}")
    public ResponseEntity<?> deleteDecadev(@PathVariable("decaId") String decaId) {
        decadevService.deleteDecadev(decaId);
        return new ResponseEntity<>("Decadev with ID '" + decaId + "' was deleted", HttpStatus.OK);
    }

}
