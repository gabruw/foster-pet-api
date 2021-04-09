package com.foster.pet.exception.person;

import com.foster.pet.constant.ErrorCode;

public class PersonNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -189761704938006043L;

	public PersonNotFoundException() {
		super(ErrorCode.PERSON_NOT_FOUND.getMessage());
	}
}
