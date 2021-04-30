package com.foster.pet.service;

import com.foster.pet.dto.authentication.AuthenticationFRPDTO;
import com.foster.pet.dto.authentication.AuthenticationRDTO;
import com.foster.pet.dto.token.TokenFRDTO;
import com.foster.pet.dto.token.TokenRDTO;
import com.foster.pet.dto.user.LoginDTO;
import com.foster.pet.entity.Authentication;

public interface AuthenticationService {

	AuthenticationFRPDTO findById(Long id);

	AuthenticationFRPDTO findByEmail(String email);

	TokenFRDTO login(LoginDTO loginDTO);

	TokenRDTO refresh(String token);

	Authentication register(Authentication authentication);

	AuthenticationFRPDTO edit(AuthenticationFRPDTO authentication);
	
	AuthenticationRDTO remove(Long id);
}
