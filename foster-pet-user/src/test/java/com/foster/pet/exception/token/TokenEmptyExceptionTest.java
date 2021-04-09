package com.foster.pet.exception.token;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Exception - TokenEmpty")
public class TokenEmptyExceptionTest {

	@Test
	@DisplayName("Throw token is empty")
	public void tokenEmptyExceptionException() {
		TokenEmptyException exception = new TokenEmptyException();
		assertEquals(exception.getMessage(), ErrorCode.TOKEN_EMPTY.getMessage());
	}
}
