package com.foster.pet.exception.authentication;

public class AuthenticationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2226994065380175735L;

	public AuthenticationNotFoundException(String message) {
		super(message);
	}
}
