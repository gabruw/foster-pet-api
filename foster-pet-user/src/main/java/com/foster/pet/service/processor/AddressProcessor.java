package com.foster.pet.service.processor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foster.pet.entity.Address;
import com.foster.pet.entity.City;
import com.foster.pet.entity.State;
import com.foster.pet.exception.city.CityNotFoundException;
import com.foster.pet.exception.state.StateNotFoundException;
import com.foster.pet.repository.CityRepository;
import com.foster.pet.repository.StateRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AddressProcessor {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateRepository stateRepository;

	public List<Address> validateToPersist(List<Address> addresses) {
		log.info("Start - AddressProcessor.validateToPersist - List<Address>: {}", addresses);

		List<Address> validatedAddresses = addresses.stream().map(address -> {
			Optional<City> optCity = this.cityRepository.findById(address.getCity().getId());
			if (optCity.isEmpty()) {
				log.error("CityNotFoundException - Id: {}", address.getCity().getId());
				throw new CityNotFoundException();
			}

			address.setCity(optCity.get());

			Optional<State> optState = this.stateRepository.findById(optCity.get().getState().getId());
			if (optState.isEmpty()) {
				log.error("StateNotFoundException - Id: {}", optCity.get().getState().getId());
				throw new StateNotFoundException();
			}

			address.getCity().setState(optState.get());
			return address;
		}).collect(Collectors.toList());

		log.info("End - AddressProcessor.validateToPersist - List<Address>: {}", validatedAddresses);
		return validatedAddresses;
	}
}
