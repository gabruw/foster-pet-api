package com.foster.pet.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.authentication.AuthenticationPersonPDTO;
import com.foster.pet.dto.person.PersonFRDTO;
import com.foster.pet.dto.person.PersonRDTO;
import com.foster.pet.entity.Address;
import com.foster.pet.entity.Authentication;
import com.foster.pet.entity.Person;
import com.foster.pet.repository.PersonRepository;
import com.foster.pet.service.AuthenticationService;
import com.foster.pet.service.PersonService;
import com.foster.pet.service.prcr.AddressProcessor;
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
	private AddressProcessor addressProcessor;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AuthenticationService authenticationService;

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
	public AuthenticationPersonPDTO register(AuthenticationPersonPDTO authenticationPersonPDTO) {
		log.info("Start - PersonServiceImpl.register - AuthenticationPersonPDTO: {}",
				authenticationPersonPDTO.toString());

		Authentication authentication = this.mapper.map(authenticationPersonPDTO, Authentication.class);
		this.personProcessor.exists(authentication.getPerson().getCpf());

		List<Address> validatedAddresses = this.addressProcessor.validade(authentication.getPerson().getAddresses());
		authentication.getPerson().setAddresses(validatedAddresses);

		authentication = this.authenticationService.register(authentication);
		authenticationPersonPDTO = this.mapper.map(authentication, AuthenticationPersonPDTO.class);

		log.info("End - PersonServiceImpl.register - AuthenticationPersonPDTO: {}",
				authenticationPersonPDTO.toString());
		return authenticationPersonPDTO;
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
