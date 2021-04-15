package com.foster.pet.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.person.PersonFRDTO;
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
	public Page<PersonRDTO> findAll(Pageable pageable) {
		log.info("Start - PersonServiceImpl.findAll");

		Page<Person> persons = this.personRepository.findAll(pageable);
		Page<PersonRDTO> personRDTO = persons.map(person -> this.mapper.map(person, PersonRDTO.class));

		log.info("End - PersonServiceImpl.findAll - Page<PersonRDTO>: {}", personRDTO.toString());
		return personRDTO;
	}

	@Override
	public PersonFRDTO findById(Long id) {
		log.info("Start - PersonServiceImpl.findById - Id: {}", id);

		Optional<Person> optPerson = this.personRepository.findById(id);
		if (optPerson.isEmpty()) {
			log.error("PersonNotFoundException - Id: {}", id);
			throw new PersonNotFoundException();
		}

		PersonFRDTO person = this.mapper.map(optPerson.get(), PersonFRDTO.class);

		log.info("End - PersonServiceImpl.findById - PersonFRDTO {}", person.toString());
		return person;
	}

	@Override
	public PersonFRDTO findByCpf(String cpf) {
		log.info("Start - PersonServiceImpl.findByCpf - CPF: {}", cpf);

		Optional<Person> optPerson = this.personRepository.findByCpf(cpf);
		if (optPerson.isEmpty()) {
			log.error("PersonNotFoundException - CPF: {}", cpf);
			throw new PersonNotFoundException();
		}

		PersonFRDTO person = this.mapper.map(optPerson.get(), PersonFRDTO.class);

		log.info("End - PersonServiceImpl.findByCpf - PersonFRDTO: {}", person.toString());
		return person;
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
		PersonRDTO person = this.mapper.map(optPerson.get(), PersonRDTO.class);

		log.info("End - PersonServiceImpl.deleteById - PersonRDTO: {}", person.toString());
		return person;
	}
}
