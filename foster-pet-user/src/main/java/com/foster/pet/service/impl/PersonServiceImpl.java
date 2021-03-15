package com.foster.pet.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foster.pet.constant.ErrorCode;
import com.foster.pet.dto.PersonDTO;
import com.foster.pet.entity.Person;
import com.foster.pet.exception.PersonNotFoundException;
import com.foster.pet.repository.PersonRepository;
import com.foster.pet.service.PersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<PersonDTO> findAll() {
		log.info("Start - PersonServiceImpl.findAll");
		List<Person> persons = this.personRepository.findAll();

		log.debug("End - PersonServiceImpl.findAll - List<Person>: {}", persons.toString());
		return persons.stream().map(person -> mapper.map(person, PersonDTO.class)).collect(Collectors.toList());
	}

	@Override
	public Person findById(Long id) {
		log.info("Start - PersonServiceImpl.findById - Id: {}", id);

		Optional<Person> optPerson = this.personRepository.findById(id);
		if (optPerson.isEmpty()) {
			log.error("PersonNotFoundException - Id: {}", id);
			throw new PersonNotFoundException(ErrorCode.PERSON_NOT_FOUND.getMessage());
		}

		log.debug("End - PersonServiceImpl.findById - Person {}", optPerson.get().toString());
		return optPerson.get();
	}

	@Override
	public Person findByCpf(String cpf) {
		log.info("Start - PersonServiceImpl.findByCpf - CPF: {}", cpf);

		Optional<Person> optPerson = this.personRepository.findByCpf(cpf);
		if (optPerson.isEmpty()) {
			log.error("PersonNotFoundException - CPF: {}", cpf);
			throw new PersonNotFoundException(ErrorCode.PERSON_NOT_FOUND.getMessage());
		}

		log.debug("End - PersonServiceImpl.findByCpf - Person: {}", optPerson.get().toString());
		return optPerson.get();
	}

	@Override
	public Person persist(Person person) {
		log.info("PersonServiceImpl.persist - Person: {}", person.toString());
		return this.personRepository.save(person);
	}

	@Override
	public PersonDTO deleteById(Long id) {
		log.info("Start - PersonServiceImpl.deleteById - Id: {}", id);

		Optional<Person> optPerson = this.personRepository.findById(id);
		if (optPerson.isEmpty()) {
			log.error("PersonNotFoundException - Id: {}", id);
			throw new PersonNotFoundException(ErrorCode.PERSON_NOT_FOUND.getMessage());
		}

		this.personRepository.deleteById(id);
		PersonDTO personDTO = mapper.map(optPerson.get(), PersonDTO.class);

		log.debug("End - PersonServiceImpl.deleteById - Person: {}", personDTO.toString());
		return personDTO;
	}
}
