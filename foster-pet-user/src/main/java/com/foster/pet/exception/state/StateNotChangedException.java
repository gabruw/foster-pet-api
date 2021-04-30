package com.foster.pet.exception.state;

import com.foster.pet.constant.ErrorCode;

public class StateNotChangedException extends RuntimeException {

	private static final long serialVersionUID = 4726309365713314402L;

	public StateNotChangedException() {
		super(ErrorCode.STATE_NOT_CHANGED.getMessage());
	}
}
