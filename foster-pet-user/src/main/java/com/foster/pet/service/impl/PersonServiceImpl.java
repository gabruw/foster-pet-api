package com.foster.pet.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foster.pet.entity.Person;
import com.foster.pet.repository.PersonRepository;
import com.foster.pet.service.PersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<Person> findAll() {
		log.info("Find All person's");
		return this.personRepository.findAll();
	}

	@Override
	public Optional<Person> findById(Long id) {
		log.info("Find person by Id: {}", id);
		return this.personRepository.findById(id);
	}

	@Override
	public Optional<Person> findByCpf(String cpf) {
		log.info("Find person by CPF: {}", cpf);
		return this.personRepository.findByCpf(cpf);
	}

	@Override
	public Person persist(Person person) {
		log.info("Persist person");
		return this.personRepository.save(person);
	}

	@Override
	public void deleteById(Long id) {
		log.info("Delete person by Id: {}", id);
		this.personRepository.deleteById(id);
	}
}
