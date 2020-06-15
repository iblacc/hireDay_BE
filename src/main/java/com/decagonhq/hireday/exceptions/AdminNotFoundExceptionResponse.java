package com.decagonhq.hireday.exceptions;

public class AdminNotFoundExceptionResponse {

    private String AdminNotFound;

    public AdminNotFoundExceptionResponse(String employerNotFound) {
        AdminNotFound = employerNotFound;
    }

    public String getAdminNotFound() {
        return AdminNotFound;
    }

    public void setAdminNotFound(String adminNotFound) {
        AdminNotFound = adminNotFound;
    }
}
