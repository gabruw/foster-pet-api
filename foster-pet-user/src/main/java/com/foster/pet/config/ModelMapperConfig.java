package com.foster.pet.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.foster.pet.conversor.AuthenticationCompanyPDTOToAuthentication;
import com.foster.pet.conversor.AuthenticationPersonPDTOToAuthentication;
import com.foster.pet.conversor.AuthenticationToJwtUser;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(new AuthenticationToJwtUser());
		modelMapper.addConverter(new AuthenticationPersonPDTOToAuthentication());
		modelMapper.addConverter(new AuthenticationCompanyPDTOToAuthentication());

		return modelMapper;
	}
}