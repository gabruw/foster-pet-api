package com.foster.pet.service.prcr;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foster.pet.dto.user.UserDTO;
import com.foster.pet.entity.Address;
import com.foster.pet.entity.City;
import com.foster.pet.entity.Company;
import com.foster.pet.entity.Country;
import com.foster.pet.entity.Person;
import com.foster.pet.entity.State;
import com.foster.pet.exception.address.AddressAlreadyExistsException;
import com.foster.pet.exception.address.AddressNotFoundException;
import com.foster.pet.exception.city.CityNotFoundException;
import com.foster.pet.exception.state.StateNotFoundException;
import com.foster.pet.exception.user.UserTypeNotRecognizedException;
import com.foster.pet.repository.AddressRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AddressProcessor {

	@Autowired
	private PersonProcessor personProcessor;

	@Autowired
	private CompanyProcessor companyProcessor;

	@Autowired
	private CountryProcessor countryProcessor;

	@Autowired
	private AddressRepository addressRepository;

	public Address exists(Long id) {
		log.info("Start - AddressProcessor.exists - Id: {}", id);

		Optional<Address> optAddress = this.addressRepository.findById(id);
		if (optAddress.isEmpty()) {
			log.error("AddressNotFoundException - Id: {}", id);
			throw new AddressNotFoundException();
		}

		log.info("End - AddressProcessor.exists - Address: {}", optAddress.get());
		return optAddress.get();
	}

	public Address exists(List<Address> addresses, String name) {
		log.info("Start - AddressProcessor.exists - Name: {}", name);

		Optional<Address> optAddress = addresses.stream().filter(address -> address.getName().equals(name)).findFirst();
		if (optAddress.isEmpty()) {
			log.error("AddressNotFoundException - Name: {}", name);
			throw new AddressNotFoundException();
		}

		log.info("End - AddressProcessor.exists - Address: {}", optAddress.get());
		return optAddress.get();
	}

	public void alreadyExists(List<Address> addresses, String name) {
		log.info("Start - AddressProcessor.alreadyExists - Name: {}", name);

		Optional<Address> optAddress = addresses.stream().filter(address -> address.getName().equals(name)).findFirst();
		if (optAddress.isPresent()) {
			log.error("AddressAlreadyExistsException - Name: {}", name);
			throw new AddressAlreadyExistsException();
		}

		log.info("End - AddressProcessor.alreadyExists");
	}

	public List<Address> validade(List<Address> addresses) {
		log.info("Start - AddressProcessor.validade - List<Address>: {}", addresses);

		List<Address> vltdAddresses = addresses.stream().map(address -> {
			Country country = this.countryProcessor.exists(address.getCity().getState().getCountry().getId());
			address.getCity().getState().setCountry(country);

			State state = this.stateFilter(address.getCity().getState().getId(), country);
			address.getCity().setState(state);

			City city = this.countryFilter(address.getCity().getId(), state);
			address.setCity(city);

			return address;
		}).collect(Collectors.toList());

		log.info("End - AddressProcessor.validade - List<Address>: {}", vltdAddresses);
		return vltdAddresses;
	}

	private State stateFilter(Long stateId, Country country) {
		log.info("Start - AddressProcessor.stateFilter - StateId: {}, Country: {}", stateId, country);

		Optional<State> optState = country.getState().stream().filter(state -> state.getId().equals(stateId))
				.findFirst();
		if (optState.isEmpty()) {
			log.error("StateNotFoundException - Id: {}", stateId);
			throw new StateNotFoundException();
		}

		log.info("End - AddressProcessor.stateFilter - State: {}", optState.get());
		return optState.get();
	}

	private City countryFilter(Long cityId, State state) {
		log.info("Start - AddressProcessor.stateFilter - CityId: {}, State: {}", cityId, state);

		Optional<City> optCity = state.getCity().stream().filter(city -> city.getId().equals(cityId)).findFirst();
		if (optCity.isEmpty()) {
			log.error("CityNotFoundException - Id: {}", cityId);
			throw new CityNotFoundException();
		}

		log.info("End - AddressProcessor.stateFilter - City: {}", optCity.get());
		return optCity.get();
	}

	public List<Address> getAddresses(UserDTO userDTO) {
		log.info("Start - AuthenticationProcessor.getAddresses - UserDTO: {}", userDTO);

		List<Address> addresses;
		switch (userDTO.getUserType()) {
		case PERSON: {
			Person person = this.personProcessor.exists(userDTO.getId());
			addresses = person.getAddresses();
			break;
		}
		case COMPANY: {
			Company company = this.companyProcessor.exists(userDTO.getId());
			addresses = company.getAddresses();
			break;
		}
		case NGO:
		case EMPLOYEE: {
			addresses = new ArrayList<>();
			break;
		}
		default:
			log.error("UserTypeNotRecognizedException - UserType: {}", userDTO.getUserType());
			throw new UserTypeNotRecognizedException();
		}

		log.info("End - AuthenticationProcessor.getAddresses - List<Address>: {}", addresses);
		return addresses;
	}
}
