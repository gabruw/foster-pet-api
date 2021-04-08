package com.foster.pet.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response")
public class ResponseTest {

	private static final String DATA = "Test data";
	private static final String ERROR_1 = "Teste error 1";
	private static final String ERROR_2 = "Teste error 2";
	private static final String ERROR_3 = "Teste error 3";

	@Mock
	Response<String> response;

	@BeforeEach
	public void init() {
		this.response = new Response<>();
		this.response.addError(ERROR_1);
		this.response.addError(ERROR_2);
		this.response.addError(ERROR_3);
	}

	@Test
	@DisplayName("Set data")
	public void setData() {
		this.response.setData(DATA);
		assertEquals(DATA, this.response.getData());
	}

	@Test
	@DisplayName("Get errors")
	public void getErrors() {
		assertEquals(ERROR_1, this.response.getErrors().get(0));
		assertEquals(ERROR_2, this.response.getErrors().get(1));
		assertEquals(ERROR_3, this.response.getErrors().get(2));
	}

	@Test
	@DisplayName("Set error list")
	public void setError() {
		this.response = new Response<>();

		List<String> errorList = new ArrayList<>();
		errorList.add(ERROR_1);
		errorList.add(ERROR_2);
		errorList.add(ERROR_3);

		this.response.setErrors(errorList);
		assertArrayEquals(this.response.getErrors().toArray(), errorList.toArray());
	}

	@Test
	@DisplayName("Has errors")
	public void hasErrors() {
		assertTrue(this.response.hasErrors());
	}

	@Test
	@DisplayName("Hasn't errors")
	public void hasntErrors() {
		this.response = new Response<>();
		assertFalse(this.response.hasErrors());
	}
}
