package com.foster.pet.exception.person;

public class PersonNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -189761704938006043L;

	public PersonNotFoundException(String message) {
		super(message);
	}
}