package com.foster.pet.exception;

public class PersonAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -7535758498527866431L;

	public PersonAlreadyExistsException(String message) {
		super(message);
	}
}
