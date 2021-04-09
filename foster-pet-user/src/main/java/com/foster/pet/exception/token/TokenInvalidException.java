package com.foster.pet.exception.token;

import com.foster.pet.constant.ErrorCode;

public class TokenInvalidException extends RuntimeException {

	private static final long serialVersionUID = -2864456512817685368L;

	public TokenInvalidException() {
		super(ErrorCode.TOKEN_INVALID.getMessage());
	}
}
