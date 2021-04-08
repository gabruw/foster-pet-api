package com.foster.pet.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.person.PersonRDTO;
import com.foster.pet.entity.Person;
import com.foster.pet.exception.person.PersonNotFoundException;
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
	public List<PersonRDTO> findAll() {
		log.info("Start - PersonServiceImpl.findAll");
		List<Person> persons = this.personRepository.findAll();

		log.info("End - PersonServiceImpl.findAll - List<Person>: {}", persons.toString());
		return persons.stream().map(person -> mapper.map(person, PersonRDTO.class)).collect(Collectors.toList());
	}

	@Override
	public Person findById(Long id) {
		log.info("Start - PersonServiceImpl.findById - Id: {}", id);

		Optional<Person> optPerson = this.personRepository.findById(id);
		if (optPerson.isEmpty()) {
			log.error("PersonNotFoundException - Id: {}", id);
			throw new PersonNotFoundException();
		}

		log.info("End - PersonServiceImpl.findById - Person {}", optPerson.get().toString());
		return optPerson.get();
	}

	@Override
	public Person findByCpf(String cpf) {
		log.info("Start - PersonServiceImpl.findByCpf - CPF: {}", cpf);

		Optional<Person> optPerson = this.personRepository.findByCpf(cpf);
		if (optPerson.isEmpty()) {
			log.error("PersonNotFoundException - CPF: {}", cpf);
			throw new PersonNotFoundException();
		}

		log.info("End - PersonServiceImpl.findByCpf - Person: {}", optPerson.get().toString());
		return optPerson.get();
	}

	@Override
	public PersonRDTO deleteById(Long id) {
		log.info("Start - PersonServiceImpl.deleteById - Id: {}", id);

		Optional<Person> optPerson = this.personRepository.findById(id);
		if (optPerson.isEmpty()) {
			log.error("PersonNotFoundException - Id: {}", id);
			throw new PersonNotFoundException();
		}

		this.personRepository.deleteById(id);
		PersonRDTO personRDTO = this.mapper.map(optPerson.get(), PersonRDTO.class);

		log.info("End - PersonServiceImpl.deleteById - PersonRDTO: {}", personRDTO.toString());
		return personRDTO;
	}
}
