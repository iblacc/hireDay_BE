package com.decagonhq.hireday.exceptions;

public class OrganizationNameExceptionResponse {

    private String organizationName;

    public OrganizationNameExceptionResponse(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
