package com.foster.pet.conversor.state;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.foster.pet.dto.country.CountryRPDTO;
import com.foster.pet.dto.state.StateRDTO;
import com.foster.pet.entity.State;

public class StateToStateRDTO implements Converter<State, StateRDTO> {

	@Override
	public StateRDTO convert(MappingContext<State, StateRDTO> context) {
		State source = context.getSource();

		CountryRPDTO country = CountryRPDTO.builder().name(source.getCountry().getName()).build();
		return StateRDTO.builder().name(source.getName()).country(country).build();
	}
}