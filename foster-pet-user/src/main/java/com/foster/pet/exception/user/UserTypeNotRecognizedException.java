package com.foster.pet.exception.user;

import com.foster.pet.constant.ErrorCode;

public class UserTypeNotRecognizedException extends RuntimeException {

	private static final long serialVersionUID = 4651373996731658004L;

	public UserTypeNotRecognizedException() {
		super(ErrorCode.USER_TYPE_NOT_RECOGNIZED.getMessage());
	}
}
