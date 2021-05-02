package com.foster.pet.conversor.state;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.foster.pet.dto.country.CountryFRPDTO;
import com.foster.pet.dto.state.StateFRDTO;
import com.foster.pet.entity.State;

public class StateFRPDTOToState implements Converter<State, StateFRDTO> {

	@Override
	public StateFRDTO convert(MappingContext<State, StateFRDTO> context) {
		State source = context.getSource();

		CountryFRPDTO country = CountryFRPDTO.builder().id(source.getCountry().getId())
				.name(source.getCountry().getName()).build();
		return StateFRDTO.builder().id(source.getId()).name(source.getName()).country(country).build();
	}
}