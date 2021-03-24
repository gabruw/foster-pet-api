package com.foster.pet.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;

@SpringBootTest
@ActiveProfiles("test")
public class CompanyAlreadyExistsExceptionTest {

    @Test
    public void companyAlreadyExistsExceptionTest() {
        CompanyAlreadyExistsException exception = new CompanyAlreadyExistsException(ErrorCode.COMPANY_ALREADY_EXISTS.toString());
        assertEquals(exception.getMessage(), ErrorCode.COMPANY_ALREADY_EXISTS.toString());
    }
}
