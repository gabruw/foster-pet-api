package com.foster.pet.service;

import java.util.Optional;

import com.foster.pet.entity.Authentication;

public interface AuthenticationService {

	Optional<Authentication> findById(Long id);

	Optional<Authentication> findByEmail(String email);

	Authentication persist(Authentication authentication);

	void deleteById(Long id);
}
