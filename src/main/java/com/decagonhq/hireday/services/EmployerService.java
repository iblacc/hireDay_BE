package com.decagonhq.hireday.services;

import com.decagonhq.hireday.dto.EmployerRegisterDTO;
import com.decagonhq.hireday.entities.Employer;
import com.decagonhq.hireday.entities.UserIdentity;
import com.decagonhq.hireday.exceptions.*;
import com.decagonhq.hireday.repositories.EmployerRepository;
import com.decagonhq.hireday.repositories.UserIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployerService {


    private UserIdentityRepository userIdentityRepository;
    private EmployerRepository employerRepository;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;

    @Autowired
    public EmployerService(UserIdentityRepository userIdentityRepository, EmployerRepository employerRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userIdentityRepository = userIdentityRepository;
        this.employerRepository = employerRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public Employer registerEmployer(EmployerRegisterDTO registerDTO) {

        if(!registerDTO.comparePasswords()) {
            throw new UserPasswordException("Passwords do not match");
        }

        boolean isRegistered = userIdentityRepository.existsByEmail(registerDTO.getEmail());
        if(isRegistered) throw new UserAlreadyExistException("User with email '" + registerDTO.getEmail() + "' already exists.");

        Employer employer = new Employer(
                registerDTO.getFirstName(),
                registerDTO.getLastName(),
                registerDTO.getEmail(),
                registerDTO.getCompanyName(),
                registerDTO.getRole(),
                passwordEncoder.encode(registerDTO.getPassword())
        );

        try {
            Employer registeredEmployer = employerRepository.save(employer);
            userIdentityRepository.save(new UserIdentity(registerDTO.getEmail()));
            sendRegistrationSuccessMail(registeredEmployer);
            return registeredEmployer;
        } catch (Exception ex) {
            throw new UnexpectedErrorException("An error has occurred, please contact the help center.");
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

    public Employer updateEmployer(long id, Employer employer, String principalEmail) {
        Optional<Employer> employer1 = employerRepository.findById(id);

        if(employer1.isEmpty()) {
            throw new EmployerNotFoundException("Employer with ID '" + id + "' does not exit");
        }

        Employer foundEmployer = employer1.get();
        if(id == employer.getId() && foundEmployer.getEmail().equals(principalEmail)) {

            Optional<UserIdentity> identity = userIdentityRepository.findByEmail(employer.getEmail());
            if(identity.isPresent() && !principalEmail.equals(identity.get().getEmail())) {
                throw new UserAlreadyExistException("Employer with email '" + employer.getEmail() + "' already exists");
            }

            return employerRepository.save(employer);
        }

        throw new EmployerNotFoundException("You cannot update another employer");
    }

    public void deleteEmployer(long id) {

        Optional<Employer> employer = employerRepository.findById(id);
        if(employer.isEmpty()) {
            throw new EmployerNotFoundException("Employer with ID '" + id + "' could not be found");
        }

        employerRepository.delete(employer.get());
        Optional<UserIdentity> identity = userIdentityRepository.findByEmail(employer.get().getEmail());
        userIdentityRepository.delete(identity.get());
    }

    private void sendRegistrationSuccessMail(Employer employer) {
        try {
            emailService.send(employer.getFirstName(), employer.getLastName(), employer.getEmail());
        } catch (Exception ex) {
            System.out.println(
                    "Failed to send email to the registered employer.\n" +
                            "You have to configuration SMTP email integration from the email provider.\n" +
                            "For Gmail, in addition to SMTP configuration, 'Less secure app access' needs to be enable at " +
                            "the following url: \n" +
                            "https://myaccount.google.com/u/0/lesssecureapps?pli=1&pageId=none"
            );
        }
    }
}
