package com.foster.pet.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.foster.pet.dto.person.PersonRDTO;
import com.foster.pet.entity.Person;
import com.foster.pet.service.PersonService;

import properties.person.PersonInstance;
import properties.person.PersonProperties;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PersonControllerTest extends PersonProperties {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ModelMapper mapper;

	@MockBean
	private PersonService personService;

	@Mock
	private Person person;

	@Mock
	private PersonRDTO personDTO;

	private final String BASE_URL = "/person";

	@BeforeEach
	public void init() {
		this.person = PersonInstance.instace();
		this.personDTO = this.mapper.map(this.person, PersonRDTO.class);
	}

	@Test
	public void getAll() throws Exception {
		List<PersonRDTO> persons = new ArrayList<>();
		persons.add(this.personDTO);

		when(this.personService.findAll()).thenReturn(persons);

		this.mockMvc.perform(get(BASE_URL)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data[0].cpf", equalTo(CPF))).andExpect(jsonPath("$.data[0].name", equalTo(NAME)))
				.andExpect(jsonPath("$.data[0].cell", equalTo(CELL)))
				.andExpect(jsonPath("$.data[0].birth", isA(String.class)))
				.andExpect(jsonPath("$.data[0].gender", equalTo(GENDER.toString())))
				.andExpect(jsonPath("$.errors").isEmpty());
	}

	@Test
	public void findById() throws Exception {
		when(this.personService.findById(ID)).thenReturn(this.person);

		System.out.println("BIRTH " + BIRTH);
		this.mockMvc.perform(get(BASE_URL).param("id", String.valueOf(ID)))
				.andExpect(jsonPath("$.data.cpf", equalTo(CPF))).andExpect(jsonPath("$.data.name", equalTo(NAME)))
				.andExpect(jsonPath("$.data.cell", equalTo(CELL)))
				.andExpect(jsonPath("$.data.birth", isA(String.class)))
				.andExpect(jsonPath("$.data.gender", equalTo(GENDER.toString()))).andExpect(status().isOk())
				.andExpect(jsonPath("$.errors").isEmpty());
	}

	@Test
	public void getByCpf() throws Exception {
		when(this.personService.findByCpf(CPF)).thenReturn(this.person);

		this.mockMvc.perform(get(BASE_URL).param("cpf", CPF)).andExpect(jsonPath("$.data.cpf", equalTo(CPF)))
				.andExpect(jsonPath("$.data.name", equalTo(NAME))).andExpect(jsonPath("$.data.cell", equalTo(CELL)))
				.andExpect(jsonPath("$.data.birth", isA(String.class)))
				.andExpect(jsonPath("$.data.gender", equalTo(GENDER.toString()))).andExpect(status().isOk())
				.andExpect(jsonPath("$.errors").isEmpty());
	}

	@Test
	public void remove() throws Exception {
		when(this.personService.deleteById(ID)).thenReturn(this.personDTO);

		this.mockMvc.perform(delete(BASE_URL).param("id", String.valueOf(ID)))
				.andExpect(jsonPath("$.data.cpf", equalTo(CPF))).andExpect(jsonPath("$.data.name", equalTo(NAME)))
				.andExpect(jsonPath("$.data.cell", equalTo(CELL)))
				.andExpect(jsonPath("$.data.birth", isA(String.class)))
				.andExpect(jsonPath("$.data.gender", equalTo(GENDER.toString()))).andExpect(status().isOk())
				.andExpect(jsonPath("$.errors").isEmpty());
	}
}
