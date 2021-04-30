package com.foster.pet.exception.country;

import com.foster.pet.constant.ErrorCode;

public class CountryNotChangedException extends RuntimeException {

	private static final long serialVersionUID = 2560003208392815372L;

	public CountryNotChangedException() {
		super(ErrorCode.CITY_NOT_CHANGED.getMessage());
	}
}
