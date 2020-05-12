package com.decagonhq.hireday.exceptions;

public class OrganizationExceptionResponse {

    private String organizationException;

    public OrganizationExceptionResponse(String organizationException) {
        this.organizationException = organizationException;
    }

    public String getOrganizationException() {
        return organizationException;
    }

    public void setOrganizationException(String organizationException) {
        this.organizationException = organizationException;
    }
}
