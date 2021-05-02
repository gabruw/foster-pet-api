package com.foster.pet.conversor.authentication;

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
			State state = State.builder().id(addressPDTO.getCity().getState().getId()).build();
			City city = City.builder().id(addressPDTO.getCity().getId()).state(state).build();

			return Address.builder().cep(addressPDTO.getCep()).name(addressPDTO.getName()).road(addressPDTO.getRoad())
					.phone(addressPDTO.getPhone()).number(addressPDTO.getNumber())
					.complement(addressPDTO.getComplement()).neighborhood(addressPDTO.getNeighborhood()).city(city)
					.build();
		}).collect(Collectors.toList());

		Company company = Company.builder().cnpj(source.getCompany().getCnpj())
				.tradeName(source.getCompany().getTradeName()).companyName(source.getCompany().getCompanyName())
				.addresses(addresses).build();

		return Authentication.builder().email(source.getEmail()).password(source.getPassword()).role(source.getRole())
				.company(company).build();
	}
}
