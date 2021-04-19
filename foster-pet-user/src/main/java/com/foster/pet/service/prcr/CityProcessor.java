package com.foster.pet.service.prcr;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foster.pet.entity.City;
import com.foster.pet.exception.city.CityAlreadyExistsException;
import com.foster.pet.exception.city.CityNotFoundException;
import com.foster.pet.repository.CityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CityProcessor {

	@Autowired
	private CityRepository cityRepository;

	public City exists(Long id) {
		log.info("Start - CityProcessor.exists - Id {}", id);

		Optional<City> optCity = this.cityRepository.findById(id);
		if (optCity.isEmpty()) {
			log.error("CityNotFoundException - Id: {}", id);
			throw new CityNotFoundException();
		}

		log.info("End - CityProcessor.exists - City {}", optCity.get());
		return optCity.get();
	}

	public City exists(String name) {
		log.info("Start - CityProcessor.exists - Name {}", name);

		Optional<City> optCity = this.cityRepository.findByName(name);
		if (optCity.isEmpty()) {
			log.error("CityNotFoundException - Name: {}", name);
			throw new CityNotFoundException();
		}

		log.info("End - CityProcessor.exists - City {}", optCity.get());
		return optCity.get();
	}

	public City alreadyExists(String name) {
		log.info("Start - CityProcessor.alreadyExists - Name {}", name);

		Optional<City> optCity = this.cityRepository.findByName(name);
		if (optCity.isPresent()) {
			log.error("CityAlreadyExistsException - Name: {}", name);
			throw new CityAlreadyExistsException();
		}

		log.info("End - CityProcessor.alreadyExists - City {}", optCity.get());
		return optCity.get();
	}
}
