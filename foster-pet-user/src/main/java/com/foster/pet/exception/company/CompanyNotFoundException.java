package com.foster.pet.exception.company;

public class CompanyNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4257719406006891658L;

    public CompanyNotFoundException(String message) {
        super(message);
    }
}
