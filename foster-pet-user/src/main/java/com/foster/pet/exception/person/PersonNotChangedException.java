package com.foster.pet.exception.person;

import com.foster.pet.constant.ErrorCode;

public class PersonNotChangedException extends RuntimeException {

	private static final long serialVersionUID = -2222384929017575472L;

	public PersonNotChangedException() {
		super(ErrorCode.PERSON_NOT_CHANGED.getMessage());
	}
}
