package com.foster.pet.exception.city;

import com.foster.pet.constant.ErrorCode;

public class CityNotChangedException extends RuntimeException {

	private static final long serialVersionUID = 3465587554371222045L;

	public CityNotChangedException() {
		super(ErrorCode.CITY_NOT_CHANGED.getMessage());
	}
}
