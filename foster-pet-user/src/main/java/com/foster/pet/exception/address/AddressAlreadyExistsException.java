package com.foster.pet.exception.address;

import com.foster.pet.constant.ErrorCode;

public class AddressAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 4940284862657338136L;

	public AddressAlreadyExistsException() {
		super(ErrorCode.ADDRESS_ALREADY_EXISTS.getMessage());
	}
}
