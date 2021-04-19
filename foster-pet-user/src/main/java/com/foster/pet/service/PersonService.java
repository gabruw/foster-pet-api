package com.foster.pet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foster.pet.dto.authentication.AuthenticationPersonPDTO;
import com.foster.pet.dto.person.PersonFRDTO;
import com.foster.pet.dto.person.PersonRDTO;

public interface PersonService {

	Page<PersonRDTO> findAll(Pageable pageable);

	PersonFRDTO findById(Long id);

	PersonFRDTO findByCpf(String cpf);

	AuthenticationPersonPDTO register(AuthenticationPersonPDTO authenticationPersonPDTO);

	PersonRDTO remove(Long id);
}
