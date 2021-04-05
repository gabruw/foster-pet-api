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

	@Test
	@DisplayName("Throw authentication not found with message")
	public void authenticationNotFoundExceptionWithMessage() {
		AuthenticationNotFoundException exception = new AuthenticationNotFoundException(
				ErrorCode.AUTHENTICATION_NOT_FOUND.getMessage());
		assertEquals(exception.getMessage(), ErrorCode.AUTHENTICATION_NOT_FOUND.getMessage());
	}

	@Test
	@DisplayName("Throw authentication not found with error code")
	public void authenticationNotFoundExceptionWithErrorCode() {
		AuthenticationNotFoundException exception = new AuthenticationNotFoundException(
				ErrorCode.AUTHENTICATION_NOT_FOUND);
		assertEquals(exception.getMessage(), ErrorCode.AUTHENTICATION_NOT_FOUND.getMessage());
	}
}
