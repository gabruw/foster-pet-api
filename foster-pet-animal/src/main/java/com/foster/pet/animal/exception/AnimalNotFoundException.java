package com.foster.pet.animal.exception;

import com.foster.pet.animal.constant.ErrorCode;

public class AnimalNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3975771801507211123L;
	
	public AnimalNotFoundException() {
		super(ErrorCode.ANIMAL_NOT_FOUND.getMessage());
	}

}
