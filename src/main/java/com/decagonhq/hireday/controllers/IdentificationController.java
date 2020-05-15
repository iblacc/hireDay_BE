package com.decagonhq.hireday.controllers;

import com.decagonhq.hireday.entities.Decadev;
import com.decagonhq.hireday.entities.Identification;
import com.decagonhq.hireday.services.IdentificationService;
import com.decagonhq.hireday.services.RequestBodyValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/ids")
@CrossOrigin
public class IdentificationController {

    private IdentificationService identificationService;
    private RequestBodyValidationService requestBodyValidationService;

    @Autowired
    public IdentificationController(IdentificationService identificationService, RequestBodyValidationService requestBodyValidationService) {
        this.identificationService = identificationService;
        this.requestBodyValidationService = requestBodyValidationService;
    }

    @PostMapping
    public ResponseEntity<?> createIdentification (@Valid @RequestBody Identification identification, BindingResult result) {

        ResponseEntity<?> errors = requestBodyValidationService.requestBodyValidation(result);
        if(errors != null) return errors;

        Identification identification1 = identificationService.createIdentification(identification);
        return new ResponseEntity<>(identification1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getIdentification(@RequestParam("decaId") String decaId) {
        Identification identification = identificationService.getIdentificationByDecaId(decaId);
        return new ResponseEntity<>(identification, HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<Iterable<?>> getAllIdentification() {
        Iterable<Identification> identifications = identificationService.getAllIdentification();
        return new ResponseEntity<>(identifications, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateIdentification(@RequestParam("id") long id, @Valid @RequestBody Identification identification) {
        Identification identification1 = identificationService.updateIdentification(id, identification);
        return new ResponseEntity<>(identification1, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteIdentification(@RequestParam("id") long id) {
        identificationService.deleteIdentification(id);
        return new ResponseEntity<>("Identification with ID '" + id + "' was deleted", HttpStatus.OK);
    }
}
