package com.foster.pet.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.foster.pet.conversor.authentication.AuthenticationCompanyPDTOToAuthentication;
import com.foster.pet.conversor.authentication.AuthenticationPersonPDTOToAuthentication;
import com.foster.pet.conversor.authentication.AuthenticationToJwtUser;
import com.foster.pet.conversor.state.StateFRPDTOToState;
import com.foster.pet.conversor.state.StatePDTOToState;
import com.foster.pet.conversor.state.StateToStateFRPDTO;
import com.foster.pet.conversor.state.StateToStateHRDTO;
import com.foster.pet.conversor.state.StateToStateRDTO;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		// State To
		modelMapper.addConverter(new StateToStateRDTO());
		modelMapper.addConverter(new StateToStateHRDTO());
		modelMapper.addConverter(new StateToStateFRPDTO());

		// To State
		modelMapper.addConverter(new StatePDTOToState());
		modelMapper.addConverter(new StateFRPDTOToState());

		// Authentication To
		modelMapper.addConverter(new AuthenticationToJwtUser());
		
		// To Authentication
		modelMapper.addConverter(new AuthenticationPersonPDTOToAuthentication());
		modelMapper.addConverter(new AuthenticationCompanyPDTOToAuthentication());

		return modelMapper;
	}
}