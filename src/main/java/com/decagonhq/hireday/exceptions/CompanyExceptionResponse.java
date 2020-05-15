package com.decagonhq.hireday.exceptions;

public class CompanyExceptionResponse {

    private String companyException;

    public CompanyExceptionResponse(String companyException) {
        this.companyException = companyException;
    }

    public String getCompanyException() {
        return companyException;
    }

    public void setCompanyException(String companyException) {
        this.companyException = companyException;
    }
}
