package com.foster.pet.conversor.state;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.foster.pet.dto.state.StateFRPDTO;
import com.foster.pet.entity.Country;
import com.foster.pet.entity.State;

public class StateToStateFRPDTO implements Converter<StateFRPDTO, State> {

	@Override
	public State convert(MappingContext<StateFRPDTO, State> context) {
		StateFRPDTO source = context.getSource();

		Country country = Country.builder().id(source.getCountry().getId()).name(source.getCountry().getName()).build();
		return State.builder().id(source.getId()).name(source.getName()).country(country).build();
	}
}