package com.foster.pet.exception.token;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Exception - TokenTypeInvalid")
public class TokenTypeInvalidExceptionTest {

	@Test
	@DisplayName("Throw token type is invalid")
	public void tokenTypeInvalidException() {
		TokenTypeInvalidException exception = new TokenTypeInvalidException();
		assertEquals(exception.getMessage(), ErrorCode.TOKEN_TYPE_INVALID.getMessage());
	}
}
