package com.foster.pet.exception.authentication;

import com.foster.pet.constant.ErrorCode;

public class AuthenticationAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -1789831594062558569L;

    public AuthenticationAlreadyExistsException() {
		super(ErrorCode.AUTHENTICATION_ALREADY_EXISTS.getMessage());
	}
}
