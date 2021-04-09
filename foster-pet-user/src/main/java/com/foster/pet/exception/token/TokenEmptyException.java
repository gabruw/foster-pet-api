package com.foster.pet.exception.token;

import com.foster.pet.constant.ErrorCode;

public class TokenEmptyException extends RuntimeException {

	private static final long serialVersionUID = -2864456512817685368L;
	
	public TokenEmptyException() {
		super(ErrorCode.TOKEN_EMPTY.getMessage());
	}
}
