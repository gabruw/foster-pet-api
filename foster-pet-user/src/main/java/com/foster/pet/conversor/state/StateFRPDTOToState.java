package com.foster.pet.conversor.state;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.foster.pet.dto.country.CountryFRPDTO;
import com.foster.pet.dto.state.StateFRPDTO;
import com.foster.pet.entity.State;

public class StateFRPDTOToState implements Converter<State, StateFRPDTO> {

	@Override
	public StateFRPDTO convert(MappingContext<State, StateFRPDTO> context) {
		State source = context.getSource();

		CountryFRPDTO country = CountryFRPDTO.builder().id(source.getCountry().getId())
				.name(source.getCountry().getName()).build();
		return StateFRPDTO.builder().id(source.getId()).name(source.getName()).country(country).build();
	}
}