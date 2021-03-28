package com.foster.pet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foster.pet.dto.authentication.AuthenticationDTO;
import com.foster.pet.dto.person.PersonRDTO;
import com.foster.pet.entity.Authentication;
import com.foster.pet.entity.Person;
import com.foster.pet.service.AuthenticationService;
import com.foster.pet.service.PersonService;
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
	private PersonService personService;

	@Autowired
	private PersonProcessor personProcessor;

	@Autowired
	private AuthenticationService authenticationService;

	@GetMapping
	@Cacheable("person")
	public ResponseEntity<Response<List<PersonRDTO>>> getAll() {
		log.info("Start - PersonController.getAll");
		Response<List<PersonRDTO>> response = new Response<List<PersonRDTO>>();

		List<PersonRDTO> persons = this.personService.findAll();
		response.setData(persons);

		log.info("End - PersonController.getAll - List<PersonDTO>: {}", persons.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("person")
	@GetMapping(params = "id")
	public ResponseEntity<Response<Person>> getById(@RequestParam Long id) {
		log.info("Start - PersonController.getById - Id: {}", id);
		Response<Person> response = new Response<Person>();

		Person person = this.personService.findById(id);
		response.setData(person);

		log.info("End - PersonController.getById - Person: {}", person.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("person")
	@GetMapping(params = "cpf")
	public ResponseEntity<Response<Person>> getByCpf(@RequestParam String cpf) {
		log.info("Start - PersonController.getByCpf - CPF: {}", cpf);
		Response<Person> response = new Response<Person>();

		Person person = this.personService.findByCpf(cpf);
		response.setData(person);

		log.info("End - PersonController.getByCpf - Person: {}", person.toString());
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<Authentication>> register(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
		log.info("Start - PersonController.persist - AuthenticationDTO: {}", authenticationDTO.toString());
		Response<Authentication> response = new Response<Authentication>();

		this.personProcessor.validateToPersist(authenticationDTO.getPerson());

		Authentication authentication = this.authenticationService.persist(authenticationDTO);
		response.setData(authentication);

		log.info("End - PersonController.persist - Authentication: {}", authentication.toString());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(params = "id")
	public ResponseEntity<Response<PersonRDTO>> remove(@RequestParam Long id) {
		log.info("Start - PersonController.remove - Id: {}", id);
		Response<PersonRDTO> response = new Response<PersonRDTO>();

		PersonRDTO person = this.personService.deleteById(id);
		response.setData(person);

		log.info("End - PersonController.remove - PersonDTO: {}", person.toString());
		return ResponseEntity.ok(response);
	}
}
