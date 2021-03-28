package com.foster.pet.exception.company;

public class CompanyAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -1789831594062558569L;

    public CompanyAlreadyExistsException(String message) {
        super(message);
    }
}
