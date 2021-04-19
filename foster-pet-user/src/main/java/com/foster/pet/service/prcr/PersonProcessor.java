package com.foster.pet.service.prcr;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foster.pet.entity.Person;
import com.foster.pet.exception.person.PersonAlreadyExistsException;
import com.foster.pet.exception.person.PersonNotFoundException;
import com.foster.pet.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PersonProcessor {

	@Autowired
	private PersonRepository personRepository;

	public Person exists(Long id) {
		log.info("Start - PersonProcessor.exists - Id: {}", id);

		Optional<Person> optPerson = this.personRepository.findById(id);
		if (optPerson.isEmpty()) {
			log.error("PersonNotFoundException - Id: {}", id);
			throw new PersonNotFoundException();
		}

		log.info("End - PersonProcessor.exists - Person: {}", optPerson.toString());
		return optPerson.get();
	}

	public Person exists(String cpf) {
		log.info("Start - PersonProcessor.exists - CPF: {}", cpf);

		Optional<Person> optPerson = this.personRepository.findByCpf(cpf);
		if (optPerson.isPresent()) {
			log.error("PersonAlreadyExistsException - CPF: {}", cpf);
			throw new PersonAlreadyExistsException();
		}

		log.info("End - PersonProcessor.exists - Person: {}", optPerson.toString());
		return optPerson.get();
	}
}
