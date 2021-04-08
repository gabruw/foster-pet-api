package com.foster.pet.service;

import com.foster.pet.dto.authentication.AuthenticationRDTO;
import com.foster.pet.dto.token.TokenRDTO;
import com.foster.pet.entity.Authentication;

public interface AuthenticationService {

	Authentication findById(Long id);

	Authentication findByEmail(String email);

	TokenRDTO refresh(String token);

	Authentication persist(Authentication authentication);

	AuthenticationRDTO deleteById(Long id);
}
