package com.foster.pet.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.OptionDTO;
import com.foster.pet.dto.country.CountryFRPDTO;
import com.foster.pet.dto.country.CountryRPDTO;
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
	public Page<CountryRPDTO> findAll(Pageable pageable) {
		log.info("Start - CountryServiceImpl.findAll - Pageable: {}", pageable);

		Page<Country> countries = this.countryRepository.findAll(pageable);
		Page<CountryRPDTO> fltCountries = countries.map(country -> this.mapper.map(country, CountryRPDTO.class));

		log.info("End - CountryServiceImpl.findAll - Page<CountryRPDTO>: {}", fltCountries.toString());
		return fltCountries;
	}

	@Override
	public List<OptionDTO<Long>> findOptions() {
		log.info("Start - CountryServiceImpl.findOptions");

		List<Country> optCountry = this.countryRepository.findAll();
		List<OptionDTO<Long>> options = optCountry.stream()
				.map(state -> new OptionDTO<Long>(state.getName(), state.getId())).collect(Collectors.toList());

		log.info("End - CountryServiceImpl.findOptions - List<OptionDTO<Long>>: {}", options.toString());
		return options;
	}

	@Override
	public CountryFRPDTO findById(Long id) {
		log.info("Start - CountryServiceImpl.findById - Id: {}", id);

		Optional<Country> optCountry = this.countryRepository.findById(id);
		if (optCountry.isEmpty()) {
			log.error("CountryNotFoundException - Id: {}", id);
			throw new CountryNotFoundException();
		}

		CountryFRPDTO country = this.mapper.map(optCountry.get(), CountryFRPDTO.class);

		log.info("End - CountryServiceImpl.findById - CountryFRPDTO {}", country.toString());
		return country;
	}

	@Override
	public CountryFRPDTO findByName(String name) {
		log.info("Start - CountryServiceImpl.findByName - Name: {}", name);

		Optional<Country> optCountry = this.countryRepository.findByName(name);
		if (optCountry.isEmpty()) {
			log.error("CountryNotFoundException - Name: {}", name);
			throw new CountryNotFoundException();
		}

		CountryFRPDTO country = this.mapper.map(optCountry.get(), CountryFRPDTO.class);

		log.info("End - CountryServiceImpl.findByName - CountryFRPDTO: {}", country.toString());
		return country;
	}

	@Override
	public CountryRPDTO persist(CountryRPDTO countryRPDTO) {
		log.info("Start - CountryServiceImpl.persist - CountryRPDTO: {}", countryRPDTO.toString());

		Optional<Country> optCountry = this.countryRepository.findByName(countryRPDTO.getName());
		if (optCountry.isPresent()) {
			log.error("CountryAlreadyExistsException - Name: {}", countryRPDTO.getName());
			throw new CountryAlreadyExistsException();
		}

		Country country = this.mapper.map(countryRPDTO, Country.class);
		country = this.countryRepository.save(country);

		countryRPDTO = this.mapper.map(country, CountryRPDTO.class);

		log.info("End - CountryServiceImpl.persist - CountryRPDTO: {}", countryRPDTO.toString());
		return countryRPDTO;
	}

	@Override
	public CountryRPDTO deleteById(Long id) {
		log.info("Start - CountryServiceImpl.deleteById - Id: {}", id);

		Optional<Country> optCountry = this.countryRepository.findById(id);
		if (optCountry.isEmpty()) {
			log.error("CountryNotFoundException - Id: {}", id);
			throw new CountryNotFoundException();
		}

		this.countryRepository.deleteById(id);
		CountryRPDTO country = this.mapper.map(optCountry.get(), CountryRPDTO.class);

		log.info("End - CountryServiceImpl.deleteById - CountryRPDTO: {}", country.toString());
		return country;
	}
}
