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
import com.foster.pet.dto.city.CityFRDTO;
import com.foster.pet.dto.city.CityHRDTO;
import com.foster.pet.dto.city.CityPDTO;
import com.foster.pet.dto.city.CityRDTO;
import com.foster.pet.entity.City;
import com.foster.pet.entity.State;
import com.foster.pet.exception.city.CityAlreadyExistsException;
import com.foster.pet.exception.city.CityNotFoundException;
import com.foster.pet.exception.state.StateNotFoundException;
import com.foster.pet.repository.CityRepository;
import com.foster.pet.repository.StateRepository;
import com.foster.pet.service.CityService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateRepository stateRepository;

	@Override
	public Page<CityHRDTO> findAll(Pageable pageable) {
		log.info("Start - CityServiceImpl.findAll - Pageable: {}", pageable);

		Page<City> cities = this.cityRepository.findAll(pageable);
		Page<CityHRDTO> fltStates = cities.map(state -> this.mapper.map(state, CityHRDTO.class));

		log.info("End - CityServiceImpl.findAll - Page<CityHRDTO>: {}", fltStates.toString());
		return fltStates;
	}

	@Override
	public List<OptionDTO<Long>> findOptions(Long stateId) {
		log.info("Start - CityServiceImpl.findOptions");

		Optional<State> optState = this.stateRepository.findById(stateId);
		if (optState.isEmpty()) {
			log.error("StateNotFoundException - StateId: {}", stateId);
			throw new StateNotFoundException();
		}

		List<OptionDTO<Long>> options = optState.get().getCity().stream()
				.map(state -> new OptionDTO<Long>(state.getName(), state.getId())).collect(Collectors.toList());

		log.info("End - CityServiceImpl.findOptions - List<OptionDTO<Long>>: {}", options.toString());
		return options;
	}

	@Override
	public CityFRDTO findById(Long id) {
		log.info("Start - CityServiceImpl.findById - Id: {}", id);

		Optional<City> optCity = this.cityRepository.findById(id);
		if (optCity.isEmpty()) {
			log.error("CityNotFoundException - Id: {}", id);
			throw new CityNotFoundException();
		}

		CityFRDTO city = this.mapper.map(optCity.get(), CityFRDTO.class);

		log.info("End - CityServiceImpl.findById - CityFRDTO {}", city.toString());
		return city;
	}

	@Override
	public CityFRDTO findByName(String name) {
		log.info("Start - CityServiceImpl.findByName - Name: {}", name);

		Optional<City> optCity = this.cityRepository.findByName(name);
		if (optCity.isEmpty()) {
			log.error("CityNotFoundException - Name: {}", name);
			throw new CityNotFoundException();
		}

		CityFRDTO city = this.mapper.map(optCity.get(), CityFRDTO.class);

		log.info("End - CityServiceImpl.findByName - CityFRDTO: {}", city.toString());
		return city;
	}

	@Override
	public CityRDTO persist(CityPDTO cityPDTO) {
		log.info("Start - CityServiceImpl.persist - StatePDTO: {}", cityPDTO.toString());

		Optional<City> optCity = this.cityRepository.findByName(cityPDTO.getName());
		if (optCity.isPresent()) {
			log.error("CityAlreadyExistsException - Name: {}", cityPDTO.getName());
			throw new CityAlreadyExistsException();
		}

		City city = this.mapper.map(cityPDTO, City.class);
		city = this.cityRepository.save(city);

		CityRDTO cityRDTO = this.mapper.map(city, CityRDTO.class);

		log.info("End - CityServiceImpl.persist - CityRDTO: {}", cityRDTO.toString());
		return cityRDTO;
	}

	@Override
	public CityHRDTO deleteById(Long id) {
		log.info("Start - CityServiceImpl.deleteById - Id: {}", id);

		Optional<City> optCity = this.cityRepository.findById(id);
		if (optCity.isEmpty()) {
			log.error("CityNotFoundException - Id: {}", id);
			throw new CityNotFoundException();
		}

		this.cityRepository.deleteById(id);
		CityHRDTO city = this.mapper.map(optCity.get(), CityHRDTO.class);

		log.info("End - CityServiceImpl.deleteById - CityHRDTO: {}", city.toString());
		return city;
	}
}
