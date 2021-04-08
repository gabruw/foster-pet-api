package com.foster.pet.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.entity.Authentication;
import com.foster.pet.service.AuthenticationService;

import properties.authentication.AuthenticationInstance;
import properties.authentication.AuthenticationProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("ServiceImpl - UserDetails")
public class UserDetailsServiceImplTest extends AuthenticationProperties {

	@MockBean
	private AuthenticationService authenticationService;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Mock
	private Authentication authentication;

	@BeforeEach
	public void init() {
		this.authentication = AuthenticationInstance.instace();
	}

	@Test
	@DisplayName("Find an user details by Username (email)")
	public void loadUserByUsername() {
		when(this.authenticationService.findByEmail(EMAIL)).thenReturn(this.authentication);

		UserDetails returnedUserDetails = this.userDetailsServiceImpl.loadUserByUsername(EMAIL);
		String authority = returnedUserDetails.getAuthorities().stream().findFirst().get().getAuthority();
		
		assertEquals(ROLE.toString(), authority);
		assertEquals(EMAIL, returnedUserDetails.getUsername());
		assertEquals(PASSWORD, returnedUserDetails.getPassword());
	}
}
