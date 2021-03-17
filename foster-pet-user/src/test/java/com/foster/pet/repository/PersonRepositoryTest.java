package com.foster.pet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.entity.Person;
import com.foster.pet.properties.person.PersonInstance;
import com.foster.pet.properties.person.PersonProperties;

@SpringBootTest
@ActiveProfiles("test")
public class PersonRepositoryTest extends PersonProperties {

	@MockBean
	private PersonRepository personRepository;

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

		List<Person> returnedPersons = this.personRepository.findAll();
		Optional<Person> optPerson = returnedPersons.stream().findFirst();

		assertEquals(ID, optPerson.get().getId());
		assertEquals(CPF, optPerson.get().getCpf());
		assertEquals(NAME, optPerson.get().getName());
		assertEquals(CELL, optPerson.get().getCell());
		assertEquals(BIRTH, optPerson.get().getBirth());
		assertEquals(GENDER, optPerson.get().getGender());
	}

	@Test
	public void findById() {
		when(this.personRepository.findById(ID)).thenReturn(Optional.of(this.person));

		Optional<Person> returnedPerson = this.personRepository.findById(ID);
		assertEquals(ID, returnedPerson.get().getId());
		assertEquals(CPF, returnedPerson.get().getCpf());
		assertEquals(NAME, returnedPerson.get().getName());
		assertEquals(CELL, returnedPerson.get().getCell());
		assertEquals(BIRTH, returnedPerson.get().getBirth());
		assertEquals(GENDER, returnedPerson.get().getGender());
	}

	@Test
	public void findByCpf() {
		when(this.personRepository.findByCpf(CPF)).thenReturn(Optional.of(this.person));

		Optional<Person> returnedPerson = this.personRepository.findByCpf(CPF);
		assertEquals(ID, returnedPerson.get().getId());
		assertEquals(CPF, returnedPerson.get().getCpf());
		assertEquals(NAME, returnedPerson.get().getName());
		assertEquals(CELL, returnedPerson.get().getCell());
		assertEquals(BIRTH, returnedPerson.get().getBirth());
		assertEquals(GENDER, returnedPerson.get().getGender());
	}

	@Test
	public void persist() {
		when(this.personRepository.save(this.person)).thenReturn(this.person);

		Person returnedPerson = this.personRepository.save(this.person);
		assertEquals(ID, returnedPerson.getId());
		assertEquals(CPF, returnedPerson.getCpf());
		assertEquals(NAME, returnedPerson.getName());
		assertEquals(CELL, returnedPerson.getCell());
		assertEquals(BIRTH, returnedPerson.getBirth());
		assertEquals(GENDER, returnedPerson.getGender());
	}

	@Test
	public void deleteById() {
		doNothing().when(this.personRepository).deleteById(ID);
		when(this.personRepository.findById(ID)).thenReturn(Optional.of(this.person));

		this.personRepository.deleteById(ID);
		verify(this.personRepository, times(1)).deleteById(ID);
	}
}
