package com.foster.pet.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foster.pet.dto.authentication.AuthenticationPersonPDTO;
import com.foster.pet.dto.person.PersonFRDTO;
import com.foster.pet.dto.person.PersonRDTO;
import com.foster.pet.entity.Address;
import com.foster.pet.entity.Authentication;
import com.foster.pet.service.AuthenticationService;
import com.foster.pet.service.PersonService;
import com.foster.pet.service.processor.AddressProcessor;
import com.foster.pet.service.processor.PersonProcessor;
import com.foster.pet.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PersonService personService;

	@Autowired
	private PersonProcessor personProcessor;

	@Autowired
	private AddressProcessor addressProcessor;

	@Autowired
	private AuthenticationService authenticationService;

	@GetMapping
	@Cacheable("person")
	public ResponseEntity<Response<Page<PersonRDTO>>> findAll(Pageable pageable) {
		log.info("Start - PersonController.findAll = Pageable: {}", pageable);
		Response<Page<PersonRDTO>> response = new Response<>();

		Page<PersonRDTO> persons = this.personService.findAll(pageable);
		response.setData(persons);

		log.info("End - PersonController.findAll - Page<PersonRDTO>: {}", persons.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("person")
	@GetMapping(params = "id")
	public ResponseEntity<Response<PersonFRDTO>> findById(@RequestParam Long id) {
		log.info("Start - PersonController.findById - Id: {}", id);
		Response<PersonFRDTO> response = new Response<>();

		PersonFRDTO person = this.personService.findById(id);
		response.setData(person);

		log.info("End - PersonController.findById - PersonFRDTO: {}", person.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("person")
	@GetMapping(params = "cpf")
	public ResponseEntity<Response<PersonFRDTO>> findByCpf(@RequestParam String cpf) {
		log.info("Start - PersonController.findByCpf - CPF: {}", cpf);
		Response<PersonFRDTO> response = new Response<>();

		PersonFRDTO person = this.personService.findByCpf(cpf);
		response.setData(person);

		log.info("End - PersonController.findByCpf - PersonFRDTO: {}", person.toString());
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<Authentication>> register(
			@RequestBody @Valid AuthenticationPersonPDTO authenticationPersonPDTO) {
		log.info("Start - PersonController.register - AuthenticationPersonPDTO: {}",
				authenticationPersonPDTO.toString());
		Response<Authentication> response = new Response<>();

		Authentication authentication = this.mapper.map(authenticationPersonPDTO, Authentication.class);
		this.personProcessor.validateToPersist(authentication.getPerson());

		List<Address> validatedAddresses = this.addressProcessor
				.validateToPersist(authentication.getPerson().getAddresses());
		authentication.getPerson().setAddresses(validatedAddresses);

		authentication = this.authenticationService.persist(authentication);
		response.setData(authentication);

		log.info("End - PersonController.register - Authentication: {}", authentication.toString());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(params = "id")
	public ResponseEntity<Response<PersonRDTO>> remove(@RequestParam Long id) {
		log.info("Start - PersonController.remove - Id: {}", id);
		Response<PersonRDTO> response = new Response<>();

		PersonRDTO person = this.personService.deleteById(id);
		response.setData(person);

		log.info("End - PersonController.remove - PersonRDTO: {}", person.toString());
		return ResponseEntity.ok(response);
	}
}
