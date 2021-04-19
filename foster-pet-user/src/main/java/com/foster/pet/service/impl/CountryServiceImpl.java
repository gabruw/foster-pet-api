package com.foster.pet.service.impl;

import java.util.List;
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
import com.foster.pet.repository.CountryRepository;
import com.foster.pet.service.CountryService;
import com.foster.pet.service.prcr.CountryProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CountryProcessor countryProcessor;

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

		Country country = this.countryProcessor.exists(id);
		CountryFRPDTO countryFRPDTO = this.mapper.map(country, CountryFRPDTO.class);

		log.info("End - CountryServiceImpl.findById - CountryFRPDTO {}", countryFRPDTO.toString());
		return countryFRPDTO;
	}

	@Override
	public CountryFRPDTO findByName(String name) {
		log.info("Start - CountryServiceImpl.findByName - Name: {}", name);

		Country country = this.countryProcessor.exists(name);
		CountryFRPDTO countryFRPDTO = this.mapper.map(country, CountryFRPDTO.class);

		log.info("End - CountryServiceImpl.findByName - CountryFRPDTO: {}", countryFRPDTO.toString());
		return countryFRPDTO;
	}

	@Override
	public CountryRPDTO register(CountryRPDTO countryRPDTO) {
		log.info("Start - CountryServiceImpl.register - CountryRPDTO: {}", countryRPDTO.toString());

		this.countryProcessor.alreadyExists(countryRPDTO.getName());

		Country country = this.mapper.map(countryRPDTO, Country.class);
		country = this.countryRepository.save(country);

		countryRPDTO = this.mapper.map(country, CountryRPDTO.class);

		log.info("End - CountryServiceImpl.register - CountryRPDTO: {}", countryRPDTO.toString());
		return countryRPDTO;
	}

	@Override
	public CountryFRPDTO edit(CountryFRPDTO countryFRPDTO) {
		log.info("Start - CountryServiceImpl.edit - CountryFRPDTO: {}", countryFRPDTO.toString());

		this.countryProcessor.exists(countryFRPDTO.getId());

		Country country = this.mapper.map(countryFRPDTO, Country.class);
		country = this.countryRepository.save(country);

		countryFRPDTO = this.mapper.map(country, CountryFRPDTO.class);

		log.info("End - CountryServiceImpl.edit - CountryFRPDTO: {}", countryFRPDTO.toString());
		return countryFRPDTO;
	}

	@Override
	public CountryRPDTO remove(Long id) {
		log.info("Start - CountryServiceImpl.remove - Id: {}", id);

		Country country = this.countryProcessor.exists(id);
		this.countryRepository.deleteById(id);

		CountryRPDTO countryRPDTO = this.mapper.map(country, CountryRPDTO.class);

		log.info("End - CountryServiceImpl.remove - CountryRPDTO: {}", countryRPDTO.toString());
		return countryRPDTO;
	}
}
