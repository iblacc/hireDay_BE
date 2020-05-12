package com.decagonhq.hireday.exceptions;

public class EmployerNotFoundExceptionResponse {

    private String EmployerNotFound;

    public EmployerNotFoundExceptionResponse(String employerNotFound) {
        EmployerNotFound = employerNotFound;
    }

    public String getEmployerNotFound() {
        return EmployerNotFound;
    }

    public void setEmployerNotFound(String employerNotFound) {
        EmployerNotFound = employerNotFound;
    }
}
