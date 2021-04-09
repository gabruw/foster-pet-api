package com.foster.pet.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.country.CountryDTO;
import com.foster.pet.entity.Country;
import com.foster.pet.exception.country.CountryAlreadyExistsException;
import com.foster.pet.exception.country.CountryNotFoundException;
import com.foster.pet.repository.CountryRepository;
import com.foster.pet.service.CountryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public List<CountryDTO> findAll() {
		log.info("Start - CountryServiceImpl.findAll");
		List<Country> countries = this.countryRepository.findAll();

		log.info("End - CountryServiceImpl.findAll - List<Country>: {}", countries.toString());
		return countries.stream().map(country -> mapper.map(country, CountryDTO.class)).collect(Collectors.toList());
	}

	@Override
	public Country findById(Long id) {
		log.info("Start - CountryServiceImpl.findById - Id: {}", id);

		Optional<Country> optCountry = this.countryRepository.findById(id);
		if (optCountry.isEmpty()) {
			log.error("CountryNotFoundException - Id: {}", id);
			throw new CountryNotFoundException();
		}

		log.info("End - CountryServiceImpl.findById - Country {}", optCountry.get().toString());
		return optCountry.get();
	}

	@Override
	public Country findByName(String name) {
		log.info("Start - CountryServiceImpl.findByName - Name: {}", name);

		Optional<Country> optCountry = this.countryRepository.findByName(name);
		if (optCountry.isEmpty()) {
			log.error("CountryNotFoundException - Name: {}", name);
			throw new CountryNotFoundException();
		}

		log.info("End - CountryServiceImpl.findByName - Country: {}", optCountry.get().toString());
		return optCountry.get();
	}

	@Override
	public Country persist(Country country) {
		log.info("Start - CountryServiceImpl.persist - Country: {}", country.toString());

		Optional<Country> optAuthentication = this.countryRepository.findByName(country.getName());
		if (optAuthentication.isPresent()) {
			log.error("CountryAlreadyExistsException - Name: {}", country.getName());
			throw new CountryAlreadyExistsException();
		}

		log.info("End - CountryServiceImpl.persist - Country: {}", country.toString());
		return this.countryRepository.save(country);
	}

	@Override
	public CountryDTO deleteById(Long id) {
		log.info("Start - CountryServiceImpl.deleteById - Id: {}", id);

		Optional<Country> optCountry = this.countryRepository.findById(id);
		if (optCountry.isEmpty()) {
			log.error("CountryNotFoundException - Id: {}", id);
			throw new CountryNotFoundException();
		}

		this.countryRepository.deleteById(id);
		CountryDTO country = this.mapper.map(optCountry.get(), CountryDTO.class);

		log.info("End - CountryServiceImpl.deleteById - CountryDTO: {}", country.toString());
		return country;
	}
}
