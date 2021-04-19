package com.foster.pet.service.prcr;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foster.pet.entity.Country;
import com.foster.pet.exception.country.CountryAlreadyExistsException;
import com.foster.pet.exception.country.CountryNotFoundException;
import com.foster.pet.repository.CountryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CountryProcessor {

	@Autowired
	private CountryRepository countryRepository;

	public Country exists(Long id) {
		log.info("Start - CountryProcessor.exists - Id {}", id);

		Optional<Country> optCountry = this.countryRepository.findById(id);
		if (optCountry.isEmpty()) {
			log.error("CountryNotFoundException - Id: {}", id);
			throw new CountryNotFoundException();
		}

		log.info("End - CountryProcessor.exists - Country {}", optCountry.get());
		return optCountry.get();
	}

	public Country exists(String name) {
		log.info("Start - CountryProcessor.exists - Name {}", name);

		Optional<Country> optCountry = this.countryRepository.findByName(name);
		if (optCountry.isEmpty()) {
			log.error("CountryNotFoundException - Name: {}", name);
			throw new CountryNotFoundException();
		}

		log.info("End - CountryProcessor.exists - Country {}", optCountry.get());
		return optCountry.get();
	}

	public Country alreadyExists(String name) {
		log.info("Start - CountryProcessor.alreadyExists - Name {}", name);

		Optional<Country> optCountry = this.countryRepository.findByName(name);
		if (optCountry.isPresent()) {
			log.error("CountryAlreadyExistsException - Name: {}", name);
			throw new CountryAlreadyExistsException();
		}

		log.info("End - CountryProcessor.alreadyExists - Country {}", optCountry.get());
		return optCountry.get();
	}
}
