package com.foster.pet.exception.company;

import com.foster.pet.constant.ErrorCode;

public class CompanyAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -1789831594062558569L;

	public CompanyAlreadyExistsException() {
		super(ErrorCode.COMPANY_ALREADY_EXISTS.getMessage());
	}
}
