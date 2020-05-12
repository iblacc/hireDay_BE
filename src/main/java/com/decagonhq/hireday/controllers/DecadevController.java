package com.decagonhq.hireday.controllers;

import com.decagonhq.hireday.entities.Decadev;
import com.decagonhq.hireday.services.DecadevService;
import com.decagonhq.hireday.services.RequestBodyValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
