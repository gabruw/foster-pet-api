package com.foster.pet.exception.authentication;

public class AuthenticationAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -1789831594062558569L;

    public AuthenticationAlreadyExistsException(String message) {
        super(message);
    }
}
