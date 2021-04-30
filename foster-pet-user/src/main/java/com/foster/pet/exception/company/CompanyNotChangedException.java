package com.foster.pet.exception.company;

import com.foster.pet.constant.ErrorCode;

public class CompanyNotChangedException extends RuntimeException {

	private static final long serialVersionUID = -8120394820860166107L;

	public CompanyNotChangedException() {
		super(ErrorCode.COMPANY_NOT_CHANGED.getMessage());
	}
}
