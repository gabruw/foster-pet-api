package com.foster.pet.exception.state;

import com.foster.pet.constant.ErrorCode;

public class StateAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -6562728260466107324L;

	public StateAlreadyExistsException() {
		super(ErrorCode.STATE_ALREADY_EXISTS.getMessage());
	}
}
