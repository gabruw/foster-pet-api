package com.foster.pet.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.foster.pet.dto.authentication.AuthenticationCompanyPDTO;
import com.foster.pet.entity.Address;
import com.foster.pet.entity.Authentication;
import com.foster.pet.entity.City;
import com.foster.pet.entity.Company;
import com.foster.pet.entity.State;

public class AuthenticationCompanyPDTOToAuthentication implements Converter<AuthenticationCompanyPDTO, Authentication> {

	@Override
	public Authentication convert(MappingContext<AuthenticationCompanyPDTO, Authentication> context) {
		AuthenticationCompanyPDTO source = context.getSource();

		List<Address> addresses = source.getCompany().getAddresses().stream().map(addressPDTO -> {
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

		Company company = new Company();
		company.setAddresses(addresses);
		company.setCnpj(source.getCompany().getCnpj());
		company.setTradeName(source.getCompany().getTradeName());
		company.setCompanyName(source.getCompany().getCompanyName());

		Authentication destination = new Authentication();
		destination.setCompany(company);
		destination.setRole(source.getRole());
		destination.setEmail(source.getEmail());
		destination.setPassword(source.getPassword());

		return destination;
	}
}
