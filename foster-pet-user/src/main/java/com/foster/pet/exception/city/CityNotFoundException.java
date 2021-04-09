package com.foster.pet.exception.city;

import com.foster.pet.constant.ErrorCode;

public class CityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1598609349812698675L;

	public CityNotFoundException() {
		super(ErrorCode.CITY_NOT_FOUND.getMessage());
	}
}
