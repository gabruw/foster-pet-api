package com.foster.pet.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;
import com.foster.pet.dto.person.PersonRDTO;
import com.foster.pet.entity.Person;
import com.foster.pet.exception.person.PersonNotFoundException;
import com.foster.pet.repository.PersonRepository;
import com.foster.pet.service.PersonService;

import properties.person.PersonInstance;
import properties.person.PersonProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("ServiceImpl - Person")
public class PersonServiceImplTest extends PersonProperties {

	@MockBean
	private PersonRepository personRepository;

	@Autowired
	private PersonService personService;

	@Mock
	private Person person;

	@BeforeEach
	public void init() {
		this.person = PersonInstance.instace();
	}

	@Test
	@DisplayName("Find all persons")
	public void findAll() {
		List<Person> persons = new ArrayList<>();
		persons.add(this.person);

		when(this.personRepository.findAll()).thenReturn(persons);

		List<PersonRDTO> returnedPersons = this.personService.findAll();
		Optional<PersonRDTO> optPerson = returnedPersons.stream().findFirst();

		assertEquals(CPF, optPerson.get().getCpf());
		assertEquals(NAME, optPerson.get().getName());
		assertEquals(CELL, optPerson.get().getCell());
		assertEquals(BIRTH, optPerson.get().getBirth());
		assertEquals(GENDER, optPerson.get().getGender());
	}

	@Test
	@DisplayName("Find a person by Id")
	public void findById() {
		when(this.personRepository.findById(ID)).thenReturn(Optional.of(this.person));

		Person returnedPerson = this.personService.findById(ID);
		assertEquals(ID, returnedPerson.getId());
		assertEquals(CPF, returnedPerson.getCpf());
		assertEquals(NAME, returnedPerson.getName());
		assertEquals(CELL, returnedPerson.getCell());
		assertEquals(BIRTH, returnedPerson.getBirth());
		assertEquals(GENDER, returnedPerson.getGender());
	}

	@Test
	@DisplayName("Find a person by Id with invalid Id")
	public void findByIdWithNotFoundPerson() {
		PersonNotFoundException exception = assertThrows(PersonNotFoundException.class, () -> {
			Long invalidId = 2L;
			when(this.personRepository.findById(ID)).thenReturn(Optional.of(this.person));

			this.personService.findById(invalidId);
		});

		assertEquals(ErrorCode.PERSON_NOT_FOUND.getMessage(), exception.getMessage());
	}

	@Test
	@DisplayName("Find a person by CPF")
	public void findByCpf() {
		when(this.personRepository.findByCpf(CPF)).thenReturn(Optional.of(this.person));

		Person returnedPerson = this.personService.findByCpf(CPF);
		assertEquals(ID, returnedPerson.getId());
		assertEquals(CPF, returnedPerson.getCpf());
		assertEquals(NAME, returnedPerson.getName());
		assertEquals(CELL, returnedPerson.getCell());
		assertEquals(BIRTH, returnedPerson.getBirth());
		assertEquals(GENDER, returnedPerson.getGender());
	}

	@Test
	@DisplayName("Find a person by CPF with invalid CPF")
	public void findByCpfWithNotFoundPerson() {
		Exception exception = assertThrows(PersonNotFoundException.class, () -> {
			String invalidCpf = "123.456.789-00";
			when(this.personRepository.findByCpf(CPF)).thenReturn(Optional.of(this.person));

			this.personService.findByCpf(invalidCpf);
		});

		assertEquals(ErrorCode.PERSON_NOT_FOUND.getMessage(), exception.getMessage());
	}

	@Test
	@DisplayName("Delete a person by Id")
	public void deleteById() {
		doNothing().when(this.personRepository).deleteById(ID);
		when(this.personRepository.findById(ID)).thenReturn(Optional.of(this.person));

		PersonRDTO returnedPersonDTO = this.personService.remove(ID);
		assertEquals(CPF, returnedPersonDTO.getCpf());
		assertEquals(NAME, returnedPersonDTO.getName());
		assertEquals(CELL, returnedPersonDTO.getCell());
		assertEquals(BIRTH, returnedPersonDTO.getBirth());
		assertEquals(GENDER, returnedPersonDTO.getGender());
		verify(this.personRepository, times(1)).deleteById(ID);
	}

	@Test
	@DisplayName("Delete a person by Id with invalid Id")
	public void deleteByIdWithInvalid() {
		Exception exception = assertThrows(PersonNotFoundException.class, () -> {
			Long invalidId = 2L;
			when(this.personRepository.findById(ID)).thenReturn(Optional.of(this.person));

			this.personService.remove(invalidId);
		});

		assertEquals(ErrorCode.PERSON_NOT_FOUND.getMessage(), exception.getMessage());
	}
}
