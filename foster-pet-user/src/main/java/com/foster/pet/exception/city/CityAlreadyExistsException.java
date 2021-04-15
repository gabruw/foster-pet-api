package com.foster.pet.exception.city;

import com.foster.pet.constant.ErrorCode;

public class CityAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 4534611554876142410L;

	public CityAlreadyExistsException() {
		super(ErrorCode.CITY_ALREADY_EXISTS.getMessage());
	}
}
