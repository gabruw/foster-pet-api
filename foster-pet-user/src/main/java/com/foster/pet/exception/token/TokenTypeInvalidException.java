package com.foster.pet.exception.token;

import com.foster.pet.constant.ErrorCode;

public class TokenTypeInvalidException extends RuntimeException {

	private static final long serialVersionUID = -6129779779617387112L;
	
	public TokenTypeInvalidException() {
		super(ErrorCode.TOKEN_TYPE_INVALID.getMessage());
	}
}
