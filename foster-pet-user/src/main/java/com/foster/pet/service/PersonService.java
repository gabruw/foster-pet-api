package com.foster.pet.service;

import java.util.List;
import java.util.Optional;

import com.foster.pet.entity.Person;

public interface PersonService {

	List<Person> findAll();

	Optional<Person> findById(Long id);

	Optional<Person> findByCpf(String cpf);
	
	Person persist(Person person);

	void deleteById(Long id);
}
