package com.foster.pet.service;

import java.util.List;

import com.foster.pet.dto.person.PersonRDTO;
import com.foster.pet.entity.Person;

public interface PersonService {

	List<PersonRDTO> findAll();

	Person findById(Long id);

	Person findByCpf(String cpf);

	PersonRDTO deleteById(Long id);
}
