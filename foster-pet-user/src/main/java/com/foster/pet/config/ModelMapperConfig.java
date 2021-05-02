package com.foster.pet.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.foster.pet.conversor.authentication.AuthenticationCompanyPDTOToAuthentication;
import com.foster.pet.conversor.authentication.AuthenticationFRPDTOToJwtUser;
import com.foster.pet.conversor.authentication.AuthenticationPersonPDTOToAuthentication;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		// Authentication To
		modelMapper.addConverter(new AuthenticationFRPDTOToJwtUser());
		
		// To Authentication
		modelMapper.addConverter(new AuthenticationPersonPDTOToAuthentication());
		modelMapper.addConverter(new AuthenticationCompanyPDTOToAuthentication());

		return modelMapper;
	}
}