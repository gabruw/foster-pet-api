package com.foster.pet.service.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.foster.pet.entity.Person;
import com.foster.pet.exception.person.PersonAlreadyExistsException;
import com.foster.pet.repository.PersonRepository;
import com.foster.pet.service.prcr.PersonProcessor;

import properties.person.PersonInstance;
import properties.person.PersonProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Processor - Person")
public class PersonProcessorTest extends PersonProperties {

	@MockBean
	private PersonRepository personRepository;

	@Autowired
	private PersonProcessor personProcessor;

	@Mock
	private Person person;

	@BeforeEach
	public void init() {
		this.person = PersonInstance.instace();
	}

	@Test
	@DisplayName("Validade person to persist")
	public void validateToPersist() {
		this.personProcessor.exists(this.person);
		verify(this.personRepository, times(1)).findByCpf(CPF);
	}

	@Test
	@DisplayName("Validade person to persist with a person already existing")
	public void persistWithAlreadyExistsCpf() {
		PersonAlreadyExistsException exception = assertThrows(PersonAlreadyExistsException.class, () -> {
			when(this.personRepository.findByCpf(CPF)).thenReturn(Optional.of(this.person));

			this.personProcessor.exists(this.person);
		});

		assertEquals(ErrorCode.PERSON_ALREADY_EXISTS.getMessage(), exception.getMessage());
	}
}
