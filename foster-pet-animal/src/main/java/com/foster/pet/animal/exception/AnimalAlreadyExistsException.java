package com.foster.pet.animal.exception;

import com.foster.pet.animal.constant.ErrorCode;

public class AnimalAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 3975771801507211123L;
	
	public AnimalAlreadyExistsException() {
		super(ErrorCode.ANIMAL_ALREADY_EXISTS.getMessage());
	}

}
