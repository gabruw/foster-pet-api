package com.foster.pet.service.prcr;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foster.pet.entity.Address;
import com.foster.pet.entity.Person;
import com.foster.pet.exception.person.PersonAlreadyExistsException;
import com.foster.pet.exception.person.PersonNotChangedException;
import com.foster.pet.exception.person.PersonNotFoundException;
import com.foster.pet.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PersonProcessor {

	@Autowired
	private AddressProcessor addressProcessor;

	@Autowired
	private PersonRepository personRepository;

	public Person exists(Long id) {
		log.info("Start - PersonProcessor.exists - Id: {}", id);

		Optional<Person> optPerson = this.personRepository.findById(id);
		if (optPerson.isEmpty()) {
			log.error("PersonNotFoundException - Id: {}", id);
			throw new PersonNotFoundException();
		}

		log.info("End - PersonProcessor.exists - Person: {}", optPerson.get());
		return optPerson.get();
	}

	public Person exists(String cpf) {
		log.info("Start - PersonProcessor.exists - CPF: {}", cpf);

		Optional<Person> optPerson = this.personRepository.findByCpf(cpf);
		if (optPerson.isEmpty()) {
			log.error("PersonNotFoundException - CPF: {}", cpf);
			throw new PersonNotFoundException();
		}

		log.info("End - PersonProcessor.exists - Person: {}", optPerson.get());
		return optPerson.get();
	}

	public void alreadyExists(String cpf) {
		log.info("Start - PersonProcessor.exists - CPF: {}", cpf);

		Optional<Person> optPerson = this.personRepository.findByCpf(cpf);
		if (optPerson.isPresent()) {
			log.error("PersonAlreadyExistsException - CPF: {}", cpf);
			throw new PersonAlreadyExistsException();
		}

		log.info("End - PersonProcessor.exists");
	}

	public Person append(Person person) {
		log.info("Start - PersonProcessor.append - Person: {}", person);

		List<Address> original = this.addressProcessor.validade(person.getAddresses());
		person.setAddresses(original);

		log.info("End - PersonProcessor.append - Person: {}", person);
		return person;
	}

	public Person merge(Person person) {
		log.info("Start - PersonProcessor.merge - Person: {}", person);

		Person original = this.exists(person.getId());
		person.setEmployee(original.getEmployee());
		person.setAddresses(original.getAddresses());
		person.setAuthentication(original.getAuthentication());

		if (Objects.equals(person, original)) {
			log.error("PersonNotChangedException - Person: {}", person);
			throw new PersonNotChangedException();
		}

		log.info("End - PersonProcessor.merge - Person: {}", person);
		return person;
	}
}
