package com.foster.pet.exception.token;

public class TokenEmptyException extends RuntimeException {

	private static final long serialVersionUID = -2864456512817685368L;

	public TokenEmptyException(String message) {
		super(message);
	}
}
