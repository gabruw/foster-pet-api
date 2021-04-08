package com.foster.pet.exception.city;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Exception - CityNotFound")
public class CityNotFoundExceptionTest {

	@Test
	@DisplayName("Throw city not found")
	public void cityNotFoundException() {
		CityNotFoundException exception = new CityNotFoundException();
		assertEquals(exception.getMessage(), ErrorCode.CITY_NOT_FOUND.getMessage());
	}

	@Test
	@DisplayName("Throw city not found with message")
	public void cityNotFoundExceptionWithMessage() {
		CityNotFoundException exception = new CityNotFoundException(ErrorCode.CITY_NOT_FOUND.getMessage());
		assertEquals(exception.getMessage(), ErrorCode.CITY_NOT_FOUND.getMessage());
	}

	@Test
	@DisplayName("Throw city not found with error code")
	public void cityNotFoundExceptionWithErrorCode() {
		CityNotFoundException exception = new CityNotFoundException(ErrorCode.CITY_NOT_FOUND);
		assertEquals(exception.getMessage(), ErrorCode.CITY_NOT_FOUND.getMessage());
	}
}
