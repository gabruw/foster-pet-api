package com.foster.pet.exception.company;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Exception - CompanyNotFound")
public class CompanyNotFoundExceptionTest {

	@Test
	@DisplayName("Throw company not found")
	public void companyNotFoundException() {
		CompanyNotFoundException exception = new CompanyNotFoundException();
		assertEquals(exception.getMessage(), ErrorCode.COMPANY_NOT_FOUND.getMessage());
	}
}
