package com.foster.pet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import com.foster.pet.service.PersonService;
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
	public ResponseEntity<Response<AuthenticationPersonPDTO>> register(
			@RequestBody @Valid AuthenticationPersonPDTO authenticationPersonPDTO) {
		log.info("Start - PersonController.register - AuthenticationPersonPDTO: {}",
				authenticationPersonPDTO.toString());
		Response<AuthenticationPersonPDTO> response = new Response<>();

		authenticationPersonPDTO = this.personService.register(authenticationPersonPDTO);
		response.setData(authenticationPersonPDTO);

		log.info("End - PersonController.register - AuthenticationPersonPDTO: {}", authenticationPersonPDTO.toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@DeleteMapping(params = "id")
	public ResponseEntity<Response<PersonRDTO>> remove(@RequestParam Long id) {
		log.info("Start - PersonController.remove - Id: {}", id);
		Response<PersonRDTO> response = new Response<>();

		PersonRDTO person = this.personService.remove(id);
		response.setData(person);

		log.info("End - PersonController.remove - PersonRDTO: {}", person.toString());
		return ResponseEntity.ok(response);
	}
}
