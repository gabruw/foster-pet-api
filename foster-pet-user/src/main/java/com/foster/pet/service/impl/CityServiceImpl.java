package com.foster.pet.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.OptionDTO;
import com.foster.pet.dto.city.CityFPDTO;
import com.foster.pet.dto.city.CityFRDTO;
import com.foster.pet.dto.city.CityHRDTO;
import com.foster.pet.dto.city.CityPDTO;
import com.foster.pet.dto.city.CityRDTO;
import com.foster.pet.entity.City;
import com.foster.pet.entity.State;
import com.foster.pet.repository.CityRepository;
import com.foster.pet.service.CityService;
import com.foster.pet.service.prcr.CityProcessor;
import com.foster.pet.service.prcr.StateProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CityProcessor cityProcessor;

	@Autowired
	private StateProcessor stateProcessor;

	@Autowired
	private CityRepository cityRepository;

	@Override
	public Page<CityHRDTO> findAll(Pageable pageable) {
		log.info("Start - CityServiceImpl.findAll - Pageable: {}", pageable);

		Page<City> cities = this.cityRepository.findAll(pageable);
		Page<CityHRDTO> fltStates = cities.map(city -> this.mapper.map(city, CityHRDTO.class));

		log.info("End - CityServiceImpl.findAll - Page<CityHRDTO>: {}", fltStates.toString());
		return fltStates;
	}

	@Override
	public List<OptionDTO<Long>> findOptions(Long stateId) {
		log.info("Start - CityServiceImpl.findOptions - StateId: {}", stateId);

		State state = this.stateProcessor.exists(stateId);
		List<OptionDTO<Long>> options = state.getCity().stream()
				.map(city -> new OptionDTO<Long>(city.getName(), city.getId())).collect(Collectors.toList());

		log.info("End - CityServiceImpl.findOptions - List<OptionDTO<Long>>: {}", options.toString());
		return options;
	}

	@Override
	public CityFRDTO findById(Long id) {
		log.info("Start - CityServiceImpl.findById - Id: {}", id);

		City city = this.cityProcessor.exists(id);
		CityFRDTO cityFRDTO = this.mapper.map(city, CityFRDTO.class);

		log.info("End - CityServiceImpl.findById - CityFRDTO {}", cityFRDTO.toString());
		return cityFRDTO;
	}

	@Override
	public CityFRDTO findByName(String name) {
		log.info("Start - CityServiceImpl.findByName - Name: {}", name);

		City city = this.cityProcessor.exists(name);
		CityFRDTO cityFRDTO = this.mapper.map(city, CityFRDTO.class);

		log.info("End - CityServiceImpl.findByName - CityFRDTO: {}", cityFRDTO.toString());
		return cityFRDTO;
	}

	@Override
	public CityRDTO register(CityPDTO cityPDTO) {
		log.info("Start - CityServiceImpl.register - CityPDTO: {}", cityPDTO.toString());

		this.cityProcessor.alreadyExists(cityPDTO.getName());

		City city = this.mapper.map(cityPDTO, City.class);
		city = this.cityRepository.save(city);

		CityRDTO cityRDTO = this.mapper.map(city, CityRDTO.class);

		log.info("End - CityServiceImpl.register - CityRDTO: {}", cityRDTO.toString());
		return cityRDTO;
	}

	@Override
	public CityFPDTO edit(CityFPDTO cityFPDTO) {
		log.info("Start - CityServiceImpl.edit - CityFPDTO: {}", cityFPDTO.toString());

		this.cityProcessor.exists(cityFPDTO.getId());

		City city = this.mapper.map(cityFPDTO, City.class);
		city = this.cityRepository.save(city);

		cityFPDTO = this.mapper.map(city, CityFPDTO.class);

		log.info("End - CityServiceImpl.edit - CityFPDTO: {}", cityFPDTO.toString());
		return cityFPDTO;
	}

	@Override
	public CityHRDTO remove(Long id) {
		log.info("Start - CityServiceImpl.remove - Id: {}", id);

		City city = this.cityProcessor.exists(id);
		this.cityRepository.deleteById(id);

		CityHRDTO cityHRDTO = this.mapper.map(city, CityHRDTO.class);

		log.info("End - CityServiceImpl.remove - CityHRDTO: {}", cityHRDTO.toString());
		return cityHRDTO;
	}
}
