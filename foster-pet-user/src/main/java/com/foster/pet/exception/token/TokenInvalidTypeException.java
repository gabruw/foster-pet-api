package com.foster.pet.exception.token;

public class TokenInvalidTypeException extends RuntimeException {

	private static final long serialVersionUID = -6129779779617387112L;

	public TokenInvalidTypeException(String message) {
		super(message);
	}
}
