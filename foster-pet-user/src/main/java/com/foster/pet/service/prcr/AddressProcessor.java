package com.foster.pet.service.prcr;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foster.pet.entity.Address;
import com.foster.pet.entity.City;
import com.foster.pet.entity.Country;
import com.foster.pet.entity.State;
import com.foster.pet.exception.city.CityNotFoundException;
import com.foster.pet.exception.state.StateNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AddressProcessor {

	@Autowired
	private CountryProcessor countryProcessor;

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
		log.info("Start - AddressProcessor.stateFilter - StateId: {}, Country: {}", stateId, country.toString());

		Optional<State> optState = country.getState().stream().filter(state -> state.getId().equals(stateId))
				.findFirst();
		if (optState.isEmpty()) {
			log.error("StateNotFoundException - Id: {}", stateId);
			throw new StateNotFoundException();
		}

		log.info("End - AddressProcessor.stateFilter - State: {}", optState.get().toString());
		return optState.get();
	}

	private City countryFilter(Long cityId, State state) {
		log.info("Start - AddressProcessor.stateFilter - CityId: {}, State: {}", cityId, state.toString());

		Optional<City> optCity = state.getCity().stream().filter(city -> city.getId().equals(cityId)).findFirst();
		if (optCity.isEmpty()) {
			log.error("CityNotFoundException - Id: {}", cityId);
			throw new CityNotFoundException();
		}

		log.info("End - AddressProcessor.stateFilter - City: {}", optCity.get().toString());
		return optCity.get();
	}
}
