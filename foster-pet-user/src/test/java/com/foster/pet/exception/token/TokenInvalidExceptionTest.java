package com.foster.pet.exception.token;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Exception - TokenInvalid")
public class TokenInvalidExceptionTest {

	@Test
	@DisplayName("Throw token is invalid")
	public void tokenInvalidException() {
		TokenInvalidException exception = new TokenInvalidException();
		assertEquals(exception.getMessage(), ErrorCode.TOKEN_INVALID.getMessage());
	}

	@Test
	@DisplayName("Throw token is invalid with message")
	public void tokenInvalidExceptionWithMessage() {
		TokenInvalidException exception = new TokenInvalidException(ErrorCode.TOKEN_INVALID.getMessage());
		assertEquals(exception.getMessage(), ErrorCode.TOKEN_INVALID.getMessage());
	}

	@Test
	@DisplayName("Throw token is invalid with error code")
	public void tokenInvalidExceptionWithErrorCode() {
		TokenInvalidException exception = new TokenInvalidException(ErrorCode.TOKEN_INVALID);
		assertEquals(exception.getMessage(), ErrorCode.TOKEN_INVALID.getMessage());
	}
}
