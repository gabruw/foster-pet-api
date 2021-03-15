package com.foster.pet.service;

import java.util.List;

import com.foster.pet.dto.PersonDTO;
import com.foster.pet.entity.Person;

public interface PersonService {

	List<PersonDTO> findAll();

	Person findById(Long id);

	Person findByCpf(String cpf);

	Person persist(Person person);

	PersonDTO deleteById(Long id);
}
