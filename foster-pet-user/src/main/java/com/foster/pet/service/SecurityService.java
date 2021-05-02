package com.foster.pet.service;

import com.foster.pet.dto.token.TokenFRDTO;
import com.foster.pet.dto.token.TokenRDTO;
import com.foster.pet.dto.user.LoginDTO;

public interface SecurityService {

	TokenFRDTO login(LoginDTO loginDTO);

	TokenRDTO refresh(String token);

	void lock(Long id);

	void unlock(Long id);

	void enable(Long id);

	void unable(Long id);
}
