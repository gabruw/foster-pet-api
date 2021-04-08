package com.foster.pet.conversor;

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
			State state = new State();
			state.setId(addressPDTO.getCity().getState().getId());

			City city = new City();
			city.setId(addressPDTO.getCity().getId());
			city.setState(state);

			Address address = new Address();
			address.setCep(addressPDTO.getCep());
			address.setName(addressPDTO.getName());
			address.setRoad(addressPDTO.getRoad());
			address.setPhone(addressPDTO.getPhone());
			address.setNumber(addressPDTO.getNumber());
			address.setComplement(addressPDTO.getComplement());
			address.setNeighborhood(addressPDTO.getNeighborhood());

			return address;
		}).collect(Collectors.toList());

		Person person = new Person();
		person.setAddresses(addresses);
		person.setCpf(source.getPerson().getCpf());
		person.setCell(source.getPerson().getCell());
		person.setName(source.getPerson().getName());
		person.setBirth(source.getPerson().getBirth());
		person.setGender(source.getPerson().getGender());

		Authentication destination = new Authentication();
		destination.setPerson(person);
		destination.setRole(source.getRole());
		destination.setEmail(source.getEmail());
		destination.setPassword(source.getPassword());

		return destination;
	}
}
