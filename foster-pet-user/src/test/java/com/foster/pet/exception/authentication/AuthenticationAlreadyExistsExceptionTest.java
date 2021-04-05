package com.foster.pet.exception.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Exception - AuthenticationAlreadyExists")
public class AuthenticationAlreadyExistsExceptionTest {

	@Test
	@DisplayName("Throw authentication already exists")
	public void authenticationAlreadyExistsException() {
		AuthenticationAlreadyExistsException exception = new AuthenticationAlreadyExistsException();
		assertEquals(exception.getMessage(), ErrorCode.AUTHENTICATION_ALREADY_EXISTS.getMessage());
	}

	@Test
	@DisplayName("Throw authentication already exists with message")
	public void authenticationAlreadyExistsExceptionWithMessage() {
		AuthenticationAlreadyExistsException exception = new AuthenticationAlreadyExistsException(
				ErrorCode.AUTHENTICATION_ALREADY_EXISTS.getMessage());
		assertEquals(exception.getMessage(), ErrorCode.AUTHENTICATION_ALREADY_EXISTS.getMessage());
	}

	@Test
	@DisplayName("Throw authentication already exists with error code")
	public void authenticationAlreadyExistsExceptionWithErrorCode() {
		AuthenticationAlreadyExistsException exception = new AuthenticationAlreadyExistsException(
				ErrorCode.AUTHENTICATION_ALREADY_EXISTS);
		assertEquals(exception.getMessage(), ErrorCode.AUTHENTICATION_ALREADY_EXISTS.getMessage());
	}
}
