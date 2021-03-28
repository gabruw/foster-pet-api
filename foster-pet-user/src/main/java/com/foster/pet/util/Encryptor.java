package com.foster.pet.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Encryptor {

	public static String encode(String value) {
		return new BCryptPasswordEncoder().encode(value);
	}

	public static Boolean match(String value, String encoded) {
		return new BCryptPasswordEncoder().matches(value, encoded);
	}
}
