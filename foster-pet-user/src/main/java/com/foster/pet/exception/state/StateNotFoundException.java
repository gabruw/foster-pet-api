package com.foster.pet.exception.state;

import com.foster.pet.constant.ErrorCode;

public class StateNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4547014243849309787L;

	public StateNotFoundException() {
		super(ErrorCode.STATE_NOT_FOUND.getMessage());
	}
}
