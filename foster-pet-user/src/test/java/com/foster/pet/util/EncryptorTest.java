package com.foster.pet.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import properties.authentication.AuthenticationProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Encryptor")
public class EncryptorTest extends AuthenticationProperties {

	@Test
	@DisplayName("Encrypt and match values")
	public void encode() {
		String encoded = Encryptor.encode(PASSWORD);

		assertTrue(Encryptor.match(PASSWORD, encoded));
	}
}
