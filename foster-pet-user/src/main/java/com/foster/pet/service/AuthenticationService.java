package com.foster.pet.service;

import com.foster.pet.dto.authentication.AuthenticationFRPDTO;
import com.foster.pet.dto.authentication.AuthenticationRDTO;
import com.foster.pet.dto.authentication.TokenRDTO;
import com.foster.pet.entity.Authentication;

public interface AuthenticationService {

	AuthenticationFRPDTO findById(Long id);

	AuthenticationFRPDTO findByEmail(String email);

	TokenRDTO refresh(String token);

	Authentication register(Authentication authentication);

	AuthenticationRDTO remove(Long id);
}
