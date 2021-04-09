package com.foster.pet.exception.country;

import com.foster.pet.constant.ErrorCode;

public class CountryAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -2676422610144429813L;

	public CountryAlreadyExistsException() {
		super(ErrorCode.COUNTRY_ALREADY_EXISTS.getMessage());
	}
}
