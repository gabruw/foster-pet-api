package com.foster.pet.exception.authentication;

import com.foster.pet.constant.ErrorCode;

public class WrongPasswordException extends RuntimeException {

	private static final long serialVersionUID = -3904671552829840705L;

	public WrongPasswordException() {
		super(ErrorCode.WRONG_PASSWORD.getMessage());
	}
}
