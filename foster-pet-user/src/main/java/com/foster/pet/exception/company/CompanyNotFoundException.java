package com.foster.pet.exception.company;

import com.foster.pet.constant.ErrorCode;

public class CompanyNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4257719406006891658L;

    public CompanyNotFoundException() {
		super(ErrorCode.COMPANY_NOT_FOUND.getMessage());
	}
}
