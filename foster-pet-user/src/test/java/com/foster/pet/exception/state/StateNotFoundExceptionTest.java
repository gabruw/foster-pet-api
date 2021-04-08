package com.foster.pet.exception.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Exception - StateNotFound")
public class StateNotFoundExceptionTest {

	@Test
	@DisplayName("Throw state not found")
	public void stateNotFoundException() {
		StateNotFoundException exception = new StateNotFoundException();
		assertEquals(exception.getMessage(), ErrorCode.STATE_NOT_FOUND.getMessage());
	}

	@Test
	@DisplayName("Throw state not found with message")
	public void stateNotFoundExceptionWithMessage() {
		StateNotFoundException exception = new StateNotFoundException(ErrorCode.STATE_NOT_FOUND.getMessage());
		assertEquals(exception.getMessage(), ErrorCode.STATE_NOT_FOUND.getMessage());
	}

	@Test
	@DisplayName("Throw state not found with error code")
	public void stateNotFoundExceptionWithErrorCode() {
		StateNotFoundException exception = new StateNotFoundException(ErrorCode.STATE_NOT_FOUND);
		assertEquals(exception.getMessage(), ErrorCode.STATE_NOT_FOUND.getMessage());
	}
}
