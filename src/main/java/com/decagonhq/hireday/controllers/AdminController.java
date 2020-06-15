package com.decagonhq.hireday.controllers;

import com.decagonhq.hireday.dto.AdminRegisterDTO;
import com.decagonhq.hireday.entities.Admin;
import com.decagonhq.hireday.entities.Employer;
import com.decagonhq.hireday.services.AdminService;
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
@RequestMapping("api/v1/management")
@CrossOrigin
public class AdminController {

    private AdminService adminService;
    private RequestBodyValidationService requestBodyValidationService;

    @Autowired
    public AdminController(AdminService adminService, RequestBodyValidationService requestBodyValidationService) {
        this.adminService = adminService;
        this.requestBodyValidationService = requestBodyValidationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerEmployer(@Valid @RequestBody AdminRegisterDTO adminRegisterDTO, BindingResult result) {

        ResponseEntity<?> errors = requestBodyValidationService.requestBodyValidation(result);
        if(errors != null) return errors;

        Admin admin = adminService.registerAdmin(adminRegisterDTO);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAdmin(@RequestParam("email") String email) {
        Admin admin = adminService.getAdminByEmail(email);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Iterable<?>> getAllAdmins() {
        Iterable<Admin> admins = adminService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateAdmin(@RequestParam("id") long id, @Valid @RequestBody Admin admin, Principal principal, BindingResult result) {

        ResponseEntity<?> errors = requestBodyValidationService.requestBodyValidation(result);
        if(errors != null) return errors;

        Admin admin1 = adminService.updateAdmin(id, admin, principal.getName());
        return new ResponseEntity<>(admin1, HttpStatus.CREATED);
    }
}
