package com.foster.pet.service.processor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foster.pet.constant.ErrorCode;
import com.foster.pet.entity.Person;
import com.foster.pet.exception.person.PersonAlreadyExistsException;
import com.foster.pet.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PersonProcessor {

	@Autowired
	private PersonRepository personRepository;

	public void validateToPersist(Person person) {
		log.info("Start - PersonProcessor.validateToPersist - Person: {}", person.toString());

		Optional<Person> optPerson = this.personRepository.findByCpf(person.getCpf());
		if (optPerson.isPresent()) {
			log.error("PersonAlreadyExistsException - CPF: {}", person.getCpf());
			throw new PersonAlreadyExistsException(ErrorCode.PERSON_ALREADY_EXISTS.getMessage());
		}

		log.info("End - PersonProcessor.validateToPersist - Person: {}", person.toString());
	}
}
