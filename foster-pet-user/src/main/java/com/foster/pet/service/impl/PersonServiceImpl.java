package com.foster.pet.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.authentication.AuthenticationPersonPDTO;
import com.foster.pet.dto.person.PersonFPDTO;
import com.foster.pet.dto.person.PersonFRDTO;
import com.foster.pet.dto.person.PersonHRDTO;
import com.foster.pet.entity.Authentication;
import com.foster.pet.entity.Person;
import com.foster.pet.repository.PersonRepository;
import com.foster.pet.service.AuthenticationService;
import com.foster.pet.service.PersonService;
import com.foster.pet.service.prcr.PersonProcessor;

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

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public Page<PersonHRDTO> findAll(Pageable pageable) {
		log.info("Start - PersonServiceImpl.findAll");

		Page<Person> persons = this.personRepository.findAll(pageable);
		Page<PersonHRDTO> personRDTO = persons.map(person -> this.mapper.map(person, PersonHRDTO.class));

		log.info("End - PersonServiceImpl.findAll - Page<PersonRDTO>: {}", personRDTO);
		return personRDTO;
	}

	@Override
	public PersonFRDTO findById(Long id) {
		log.info("Start - PersonServiceImpl.findById - Id: {}", id);

		Person person = this.personProcessor.exists(id);
		PersonFRDTO personFRDTO = this.mapper.map(person, PersonFRDTO.class);

		log.info("End - PersonServiceImpl.findById - PersonFRDTO {}", personFRDTO);
		return personFRDTO;
	}

	@Override
	public PersonFRDTO findByCpf(String cpf) {
		log.info("Start - PersonServiceImpl.findByCpf - CPF: {}", cpf);

		Person person = this.personProcessor.exists(cpf);
		PersonFRDTO personFRDTO = this.mapper.map(person, PersonFRDTO.class);

		log.info("End - PersonServiceImpl.findByCpf - PersonFRDTO: {}", personFRDTO);
		return personFRDTO;
	}

	@Override
	public AuthenticationPersonPDTO register(AuthenticationPersonPDTO authenticationPersonPDTO) {
		log.info("Start - PersonServiceImpl.register - AuthenticationPersonPDTO: {}", authenticationPersonPDTO);

		this.personProcessor.alreadyExists(authenticationPersonPDTO.getPerson().getCpf());
		Authentication authentication = this.mapper.map(authenticationPersonPDTO, Authentication.class);

		Person person = this.personProcessor.append(authentication.getPerson());
		authentication.setPerson(person);

		authentication = this.authenticationService.register(authentication);
		authenticationPersonPDTO = this.mapper.map(authentication, AuthenticationPersonPDTO.class);

		log.info("End - PersonServiceImpl.register - AuthenticationPersonPDTO: {}", authenticationPersonPDTO);
		return authenticationPersonPDTO;
	}

	@Override
	public PersonFPDTO edit(PersonFPDTO personFPDTO) {
		log.info("Start - PersonServiceImpl.edit - PersonFPDTO: {}", personFPDTO);

		this.personProcessor.exists(personFPDTO.getCpf());
		Authentication authentication = this.mapper.map(personFPDTO, Authentication.class);

		Person person = this.personProcessor.append(authentication.getPerson());
		authentication.setPerson(person);

		authentication = this.authenticationService.register(authentication);
		personFPDTO = this.mapper.map(authentication, PersonFPDTO.class);

		log.info("End - PersonServiceImpl.edit - PersonFPDTO: {}", personFPDTO);
		return personFPDTO;
	}

	@Override
	public PersonHRDTO remove(Long id) {
		log.info("Start - PersonServiceImpl.remove - Id: {}", id);

		Person person = this.personProcessor.exists(id);
		this.personRepository.deleteById(id);

		PersonHRDTO personRDTO = this.mapper.map(person, PersonHRDTO.class);

		log.info("End - PersonServiceImpl.remove - PersonRDTO: {}", personRDTO);
		return personRDTO;
	}
}
