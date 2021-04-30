package com.foster.pet.exception.address;

import com.foster.pet.constant.ErrorCode;

public class AddressNotChangedException extends RuntimeException {

	private static final long serialVersionUID = 3465587554371222045L;

	public AddressNotChangedException() {
		super(ErrorCode.CITY_NOT_CHANGED.getMessage());
	}
}
