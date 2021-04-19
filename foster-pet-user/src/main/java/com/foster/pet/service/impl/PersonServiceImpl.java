package com.foster.pet.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.person.PersonFRDTO;
import com.foster.pet.dto.person.PersonRDTO;
import com.foster.pet.entity.Person;
import com.foster.pet.repository.PersonRepository;
import com.foster.pet.service.PersonService;
import com.foster.pet.service.processor.PersonProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PersonProcessor personProcessor;

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

		Person person = this.personProcessor.exists(id);
		PersonFRDTO personFRDTO = this.mapper.map(person, PersonFRDTO.class);

		log.info("End - PersonServiceImpl.findById - PersonFRDTO {}", personFRDTO.toString());
		return personFRDTO;
	}

	@Override
	public PersonFRDTO findByCpf(String cpf) {
		log.info("Start - PersonServiceImpl.findByCpf - CPF: {}", cpf);

		Person person = this.personProcessor.exists(cpf);
		PersonFRDTO personFRDTO = this.mapper.map(person, PersonFRDTO.class);

		log.info("End - PersonServiceImpl.findByCpf - PersonFRDTO: {}", personFRDTO.toString());
		return personFRDTO;
	}

	@Override
	public PersonRDTO remove(Long id) {
		log.info("Start - PersonServiceImpl.remove - Id: {}", id);

		Person person = this.personProcessor.exists(id);
		this.personRepository.deleteById(id);

		PersonRDTO personRDTO = this.mapper.map(person, PersonRDTO.class);

		log.info("End - PersonServiceImpl.remove - PersonRDTO: {}", personRDTO.toString());
		return personRDTO;
	}
}
