package com.foster.pet.conversor.state;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.foster.pet.dto.state.StateHRDTO;
import com.foster.pet.entity.State;

public class StateToStateHRDTO implements Converter<State, StateHRDTO> {

	@Override
	public StateHRDTO convert(MappingContext<State, StateHRDTO> context) {
		State source = context.getSource();

		return StateHRDTO.builder().name(source.getName()).build();
	}
}