package com.foster.pet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foster.pet.dto.PersonDTO;
import com.foster.pet.entity.Person;
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
	public ResponseEntity<Response<List<PersonDTO>>> getAll() {
		log.info("Start - PersonController.findAll");
		Response<List<PersonDTO>> response = new Response<List<PersonDTO>>();

		List<PersonDTO> persons = this.personService.findAll();
		response.setData(persons);

		log.debug("End - PersonController.findAll - List<PersonDTO>: {}", persons.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("person")
	@GetMapping(params = "id")
	public ResponseEntity<Response<Person>> getById(@RequestParam Long id) {
		log.info("Start - PersonController.findById - Id: {}", id);
		Response<Person> response = new Response<Person>();

		Person person = this.personService.findById(id);
		response.setData(person);

		log.debug("End - PersonController.findById - Person: {}", person.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("person")
	@GetMapping(params = "cpf")
	public ResponseEntity<Response<Person>> getByCpf(@RequestParam String cpf) {
		log.info("Start - PersonController.findByCpf - CPF: {}", cpf);
		Response<Person> response = new Response<Person>();

		Person person = this.personService.findByCpf(cpf);
		response.setData(person);

		log.debug("End - PersonController.findByCpf - Person: {}", person.toString());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(params = "id")
	public ResponseEntity<Response<PersonDTO>> remove(@RequestParam Long id) {
		log.info("Start - PersonController.findById - Id: {}", id);
		Response<PersonDTO> response = new Response<PersonDTO>();

		PersonDTO person = this.personService.deleteById(id);
		response.setData(person);

		log.debug("End - PersonController.findById - PersonDTO: {}", person.toString());
		return ResponseEntity.ok(response);
	}
}
