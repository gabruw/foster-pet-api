package com.foster.pet.conversor.state;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.foster.pet.dto.state.StateFRDTO;
import com.foster.pet.entity.Country;
import com.foster.pet.entity.State;

public class StateToStateFRPDTO implements Converter<StateFRDTO, State> {

	@Override
	public State convert(MappingContext<StateFRDTO, State> context) {
		StateFRDTO source = context.getSource();

		Country country = Country.builder().id(source.getCountry().getId()).name(source.getCountry().getName()).build();
		return State.builder().id(source.getId()).name(source.getName()).country(country).build();
	}
}