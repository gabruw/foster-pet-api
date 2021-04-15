package com.foster.pet.service.processor;

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
import com.foster.pet.exception.country.CountryNotFoundException;
import com.foster.pet.exception.state.StateNotFoundException;
import com.foster.pet.repository.CountryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AddressProcessor {

	@Autowired
	private CountryRepository countryRepository;

	public List<Address> validateToPersist(List<Address> addresses) {
		log.info("Start - AddressProcessor.validateToPersist - List<Address>: {}", addresses);

		List<Address> validatedAddresses = addresses.stream().map(address -> {
			Long countryId = address.getCity().getState().getCountry().getId();

			Optional<Country> optCountry = this.countryRepository.findById(countryId);
			if (optCountry.isEmpty()) {
				log.error("CountryNotFoundException - Id: {}", countryId);
				throw new CountryNotFoundException();
			}

			address.getCity().getState().setCountry(optCountry.get());

			Long stateId = address.getCity().getState().getId();
			Optional<State> optState = optCountry.get().getState().stream()
					.filter(state -> state.getId().equals(stateId)).findFirst();
			if (optState.isEmpty()) {
				log.error("StateNotFoundException - Id: {}", stateId);
				throw new StateNotFoundException();
			}

			address.getCity().setState(optState.get());

			Long cityId = address.getCity().getId();
			Optional<City> optCity = optState.get().getCity().stream().filter(city -> city.getId().equals(cityId))
					.findFirst();
			if (optCity.isEmpty()) {
				log.error("CityNotFoundException - Id: {}", cityId);
				throw new CityNotFoundException();
			}

			address.setCity(optCity.get());
			return address;
		}).collect(Collectors.toList());

		log.info("End - AddressProcessor.validateToPersist - List<Address>: {}", validatedAddresses);
		return validatedAddresses;
	}
}
