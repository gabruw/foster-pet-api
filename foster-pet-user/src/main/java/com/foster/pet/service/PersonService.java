package com.foster.pet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foster.pet.dto.authentication.AuthenticationPersonPDTO;
import com.foster.pet.dto.person.PersonFRDTO;
import com.foster.pet.dto.person.PersonHRDTO;

public interface PersonService {

	Page<PersonHRDTO> findAll(Pageable pageable);

	PersonFRDTO findById(Long id);

	PersonFRDTO findByCpf(String cpf);

	AuthenticationPersonPDTO register(AuthenticationPersonPDTO authenticationPersonPDTO);

	PersonHRDTO remove(Long id);
}
