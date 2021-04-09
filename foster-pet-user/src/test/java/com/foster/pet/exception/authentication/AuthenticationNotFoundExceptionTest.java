package com.foster.pet.exception.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Exception - AuthenticationNotFound")
public class AuthenticationNotFoundExceptionTest {

	@Test
	@DisplayName("Throw authentication not found")
	public void authenticationNotFoundException() {
		AuthenticationNotFoundException exception = new AuthenticationNotFoundException();
		assertEquals(exception.getMessage(), ErrorCode.AUTHENTICATION_NOT_FOUND.getMessage());
	}
}
