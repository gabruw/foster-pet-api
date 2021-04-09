package com.foster.pet.exception.country;

import com.foster.pet.constant.ErrorCode;

public class CountryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7218256259154556141L;

	public CountryNotFoundException() {
		super(ErrorCode.COUNTRY_NOT_FOUND.getMessage());
	}
}
