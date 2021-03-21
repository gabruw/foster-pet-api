package com.foster.pet.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;

@SpringBootTest
@ActiveProfiles("test")
public class CompanyNotFoundExceptionTest {

    @Test
    public void companyNotFoundExceptionTest() {
        CompanyNotFoundException exception = new CompanyNotFoundException(ErrorCode.COMPANY_NOT_FOUND.toString());
        assertEquals(exception.getMessage(), ErrorCode.COMPANY_NOT_FOUND.toString());
    }
}
