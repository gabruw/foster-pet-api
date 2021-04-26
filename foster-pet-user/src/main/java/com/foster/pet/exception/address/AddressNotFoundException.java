package com.foster.pet.exception.address;

import com.foster.pet.constant.ErrorCode;

public class AddressNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5257531610070484546L;

	public AddressNotFoundException() {
		super(ErrorCode.ADDRESS_NOT_FOUND.getMessage());
	}
}
