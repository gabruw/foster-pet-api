package com.foster.pet.conversor.state;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.foster.pet.dto.state.StatePDTO;
import com.foster.pet.entity.Country;
import com.foster.pet.entity.State;

public class StatePDTOToState implements Converter<StatePDTO, State> {

	@Override
	public State convert(MappingContext<StatePDTO, State> context) {
		StatePDTO source = context.getSource();

		Country country = Country.builder().id(source.getCountry().getId()).build();
		return State.builder().name(source.getName()).country(country).build();
	}
}