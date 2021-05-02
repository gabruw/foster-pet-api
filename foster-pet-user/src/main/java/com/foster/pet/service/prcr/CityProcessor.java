package com.foster.pet.service.prcr;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foster.pet.entity.City;
import com.foster.pet.entity.State;
import com.foster.pet.exception.city.CityAlreadyExistsException;
import com.foster.pet.exception.city.CityNotChangedException;
import com.foster.pet.exception.city.CityNotFoundException;
import com.foster.pet.repository.CityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CityProcessor {

	@Autowired
	private StateProcessor stateProcessor;

	@Autowired
	private CityRepository cityRepository;

	public City exists(Long id) {
		log.info("Start - CityProcessor.exists - Id: {}", id);

		Optional<City> optCity = this.cityRepository.findById(id);
		if (optCity.isEmpty()) {
			log.error("CityNotFoundException - Id: {}", id);
			throw new CityNotFoundException();
		}

		log.info("End - CityProcessor.exists - City: {}", optCity.get());
		return optCity.get();
	}

	public City exists(String name) {
		log.info("Start - CityProcessor.exists - Name: {}", name);

		Optional<City> optCity = this.cityRepository.findByName(name);
		if (optCity.isEmpty()) {
			log.error("CityNotFoundException - Name: {}", name);
			throw new CityNotFoundException();
		}

		log.info("End - CityProcessor.exists - City: {}", optCity.get());
		return optCity.get();
	}

	public void alreadyExists(String name) {
		log.info("Start - CityProcessor.alreadyExists - Name: {}", name);

		Optional<City> optCity = this.cityRepository.findByName(name);
		if (optCity.isPresent()) {
			log.error("CityAlreadyExistsException - Name: {}", name);
			throw new CityAlreadyExistsException();
		}

		log.info("End - CityProcessor.alreadyExists");
	}

	public City append(City city) {
		log.info("Start - CityProcessor.append - City: {}", city);

		State original = this.stateProcessor.exists(city.getState().getId());
		city.setState(original);

		log.info("End - CityProcessor.append - City: {}", city);
		return city;
	}

	public City merge(City city) {
		log.info("Start - CityProcessor.merge - City: {}", city);

		City original = this.exists(city.getId());
		city.setState(original.getState());
		city.setAddresses(original.getAddresses());

		if (Objects.equals(city, original)) {
			log.error("CityNotChangedException - City: {}", city);
			throw new CityNotChangedException();
		}

		log.info("End - CityProcessor.merge - City: {}", city);
		return city;
	}
}
