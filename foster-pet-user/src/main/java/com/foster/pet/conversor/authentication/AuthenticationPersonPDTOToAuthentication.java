package com.foster.pet.conversor.authentication;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.foster.pet.dto.authentication.AuthenticationPersonPDTO;
import com.foster.pet.entity.Address;
import com.foster.pet.entity.Authentication;
import com.foster.pet.entity.City;
import com.foster.pet.entity.Person;
import com.foster.pet.entity.State;

public class AuthenticationPersonPDTOToAuthentication implements Converter<AuthenticationPersonPDTO, Authentication> {

	@Override
	public Authentication convert(MappingContext<AuthenticationPersonPDTO, Authentication> context) {
		AuthenticationPersonPDTO source = context.getSource();

		List<Address> addresses = source.getPerson().getAddresses().stream().map(addressPDTO -> {
			State state = State.builder().id(addressPDTO.getCity().getState().getId()).build();
			City city = City.builder().id(addressPDTO.getCity().getId()).state(state).build();

			return Address.builder().cep(addressPDTO.getCep()).name(addressPDTO.getName()).road(addressPDTO.getRoad())
					.phone(addressPDTO.getPhone()).number(addressPDTO.getNumber())
					.complement(addressPDTO.getComplement()).neighborhood(addressPDTO.getNeighborhood()).city(city)
					.build();
		}).collect(Collectors.toList());

		Person person = Person.builder().cpf(source.getPerson().getCpf()).cell(source.getPerson().getCell())
				.name(source.getPerson().getName()).birth(source.getPerson().getBirth())
				.gender(source.getPerson().getGender()).addresses(addresses).build();

		return Authentication.builder().email(source.getEmail()).password(source.getPassword()).role(source.getRole())
				.person(person).build();
	}
}
