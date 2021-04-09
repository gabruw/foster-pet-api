package com.foster.pet.exception.person;

import com.foster.pet.constant.ErrorCode;

public class PersonAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -7535758498527866431L;

	public PersonAlreadyExistsException() {
		super(ErrorCode.PERSON_ALREADY_EXISTS.getMessage());
	}
}
