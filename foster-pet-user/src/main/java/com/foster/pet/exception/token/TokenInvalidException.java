package com.foster.pet.exception.token;

public class TokenInvalidException extends RuntimeException {

	private static final long serialVersionUID = -2864456512817685368L;

	public TokenInvalidException(String message) {
		super(message);
	}
}
