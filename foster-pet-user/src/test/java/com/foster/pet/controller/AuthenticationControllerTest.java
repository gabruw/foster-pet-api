package com.foster.pet.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.foster.pet.constant.ErrorCode;
import com.foster.pet.dto.authentication.TokenRDTO;
import com.foster.pet.entity.Authentication;
import com.foster.pet.exception.token.TokenEmptyException;
import com.foster.pet.service.AuthenticationService;

import properties.Routes;
import properties.authentication.AuthenticationInstance;
import properties.authentication.AuthenticationProperties;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Controller - Authentication")
public class AuthenticationControllerTest extends AuthenticationProperties {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuthenticationService authenticationService;

	@Mock
	private Authentication authentication;

	@Mock
	private TokenRDTO tokenRDTO;

	@BeforeEach
	public void init() {
		this.authentication = AuthenticationInstance.instace();
		this.tokenRDTO = AuthenticationInstance.tokenRDTOInstance();
	}

	@Test
	public void findById() throws Exception {
		when(this.authenticationService.findById(ID)).thenReturn(this.authentication);

		this.mockMvc.perform(get(Routes.AUTHENTICATION).param("id", String.valueOf(ID))).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id", equalTo(Integer.valueOf(ID.toString()))))
				.andExpect(jsonPath("$.data.role", equalTo(ROLE.toString())))
				.andExpect(jsonPath("$.data.email", equalTo(EMAIL)))
				.andExpect(jsonPath("$.data.password", equalTo(PASSWORD))).andExpect(jsonPath("$.errors").isEmpty());
	}

	@Test
	public void getByEmail() throws Exception {
		when(this.authenticationService.findByEmail(EMAIL)).thenReturn(this.authentication);

		this.mockMvc.perform(get(Routes.AUTHENTICATION).param("email", EMAIL)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id", equalTo(Integer.valueOf(ID.toString()))))
				.andExpect(jsonPath("$.data.role", equalTo(ROLE.toString())))
				.andExpect(jsonPath("$.data.email", equalTo(EMAIL)))
				.andExpect(jsonPath("$.data.password", equalTo(PASSWORD))).andExpect(jsonPath("$.errors").isEmpty());
	}

	@Test
	public void refreshWithoutToken() throws Exception {
		this.mockMvc.perform(get(Routes.REFRESH)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]", equalTo(ErrorCode.TOKEN_EMPTY.getMessage())))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof TokenEmptyException))
				.andExpect(result -> assertEquals(ErrorCode.TOKEN_EMPTY.getMessage(),
						result.getResolvedException().getMessage()));
	}
}
