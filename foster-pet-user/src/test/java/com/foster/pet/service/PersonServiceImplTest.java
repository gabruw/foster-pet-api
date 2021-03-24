package com.foster.pet.service;

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
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;
import com.foster.pet.dto.PersonDTO;
import com.foster.pet.entity.Person;
import com.foster.pet.exception.PersonAlreadyExistsException;
import com.foster.pet.exception.PersonNotFoundException;
import com.foster.pet.properties.person.PersonInstance;
import com.foster.pet.properties.person.PersonProperties;
import com.foster.pet.repository.PersonRepository;

@SpringBootTest
@ActiveProfiles("test")
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
	public void findAll() {
		List<Person> persons = new ArrayList<>();
		persons.add(this.person);

		when(this.personRepository.findAll()).thenReturn(persons);

		List<PersonDTO> returnedPersons = this.personService.findAll();
		Optional<PersonDTO> optPerson = returnedPersons.stream().findFirst();

		assertEquals(CPF, optPerson.get().getCpf());
		assertEquals(NAME, optPerson.get().getName());
		assertEquals(CELL, optPerson.get().getCell());
		assertEquals(BIRTH, optPerson.get().getBirth());
		assertEquals(GENDER, optPerson.get().getGender());
	}

	@Test
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
	public void findByIdWithNotFoundPerson() {
		Exception exception = assertThrows(PersonNotFoundException.class, () -> {
			Long invalidId = 2L;
			when(this.personRepository.findById(ID)).thenReturn(Optional.of(this.person));

			this.personService.findById(invalidId);
		});

		assertEquals(ErrorCode.PERSON_NOT_FOUND.getMessage(), exception.getMessage());
	}

	@Test
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
	public void findByCpfWithNotFoundPerson() {
		Exception exception = assertThrows(PersonNotFoundException.class, () -> {
			String invalidCpf = "123.456.789-00";
			when(this.personRepository.findByCpf(CPF)).thenReturn(Optional.of(this.person));

			this.personService.findByCpf(invalidCpf);
		});

		assertEquals(ErrorCode.PERSON_NOT_FOUND.getMessage(), exception.getMessage());
	}

	@Test
	public void persist() {
		when(this.personRepository.save(this.person)).thenReturn(this.person);

		Person returnedPerson = this.personService.persist(this.person);
		assertEquals(ID, returnedPerson.getId());
		assertEquals(CPF, returnedPerson.getCpf());
		assertEquals(NAME, returnedPerson.getName());
		assertEquals(CELL, returnedPerson.getCell());
		assertEquals(BIRTH, returnedPerson.getBirth());
		assertEquals(GENDER, returnedPerson.getGender());
	}

	@Test
	public void persistWithAlreadyExistsCpf() {
		Exception exception = assertThrows(PersonAlreadyExistsException.class, () -> {
			when(this.personRepository.findByCpf(CPF)).thenReturn(Optional.of(this.person));

			this.personService.persist(this.person);
		});

		assertEquals(ErrorCode.PERSON_ALREADY_EXISTS.getMessage(), exception.getMessage());
	}

	@Test
	public void deleteById() {
		doNothing().when(this.personRepository).deleteById(ID);
		when(this.personRepository.findById(ID)).thenReturn(Optional.of(this.person));

		PersonDTO returnedPersonDTO = this.personService.deleteById(ID);
		assertEquals(CPF, returnedPersonDTO.getCpf());
		assertEquals(NAME, returnedPersonDTO.getName());
		assertEquals(CELL, returnedPersonDTO.getCell());
		assertEquals(BIRTH, returnedPersonDTO.getBirth());
		assertEquals(GENDER, returnedPersonDTO.getGender());
		verify(this.personRepository, times(1)).deleteById(ID);
	}

	@Test
	public void deleteByIdWithInvalid() {
		Exception exception = assertThrows(PersonNotFoundException.class, () -> {
			Long invalidId = 2L;
			when(this.personRepository.findById(ID)).thenReturn(Optional.of(this.person));

			this.personService.deleteById(invalidId);
		});

		assertEquals(ErrorCode.PERSON_NOT_FOUND.getMessage(), exception.getMessage());
	}
}
