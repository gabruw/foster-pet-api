package com.foster.pet.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.OptionDTO;
import com.foster.pet.dto.state.StateFRPDTO;
import com.foster.pet.dto.state.StateHRDTO;
import com.foster.pet.dto.state.StatePDTO;
import com.foster.pet.dto.state.StateRDTO;
import com.foster.pet.entity.Country;
import com.foster.pet.entity.State;
import com.foster.pet.repository.StateRepository;
import com.foster.pet.service.StateService;
import com.foster.pet.service.prcr.CountryProcessor;
import com.foster.pet.service.prcr.StateProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private StateProcessor stateProcessor;

	@Autowired
	private CountryProcessor countryProcessor;

	@Autowired
	private StateRepository stateRepository;

	@Override
	public Page<StateHRDTO> findAll(Pageable pageable) {
		log.info("Start - StateServiceImpl.findAll - Pageable: {}", pageable);

		Page<State> states = this.stateRepository.findAll(pageable);
		Page<StateHRDTO> fltStates = states.map(state -> this.mapper.map(state, StateHRDTO.class));

		log.info("End - StateServiceImpl.findAll - Page<StateHRDTO>: {}", fltStates);
		return fltStates;
	}

	@Override
	public List<OptionDTO<Long>> findOptions(Long countryId) {
		log.info("Start - StateServiceImpl.findOptions - CountryId: {}", countryId);

		Country country = this.countryProcessor.exists(countryId);
		List<OptionDTO<Long>> options = country.getState().stream()
				.map(state -> new OptionDTO<Long>(state.getName(), state.getId())).collect(Collectors.toList());

		log.info("End - StateServiceImpl.findOptions - List<OptionDTO<Long>>: {}", options);
		return options;
	}

	@Override
	public StateFRPDTO findById(Long id) {
		log.info("Start - StateServiceImpl.findById - Id: {}", id);

		State state = this.stateProcessor.exists(id);
		StateFRPDTO stateFRDTO = this.mapper.map(state, StateFRPDTO.class);

		log.info("End - StateServiceImpl.findById - StateFRPDTO: {}", stateFRDTO);
		return stateFRDTO;
	}

	@Override
	public StateFRPDTO findByName(String name) {
		log.info("Start - StateServiceImpl.findByName - Name: {}", name);

		State state = this.stateProcessor.exists(name);
		StateFRPDTO stateFRDTO = this.mapper.map(state, StateFRPDTO.class);

		log.info("End - StateServiceImpl.findByName - StateFRPDTO: {}", stateFRDTO);
		return stateFRDTO;
	}

	@Override
	public StateRDTO register(StatePDTO statePDTO) {
		log.info("Start - StateServiceImpl.register - StatePDTO: {}", statePDTO);

		this.stateProcessor.alreadyExists(statePDTO.getName());

		State state = this.mapper.map(statePDTO, State.class);
		state = this.stateProcessor.append(state);

		state = this.stateRepository.save(state);
		StateRDTO stateRDTO = this.mapper.map(state, StateRDTO.class);

		log.info("End - StateServiceImpl.register - StateRDTO: {}", stateRDTO);
		return stateRDTO;
	}

	@Override
	public StateFRPDTO edit(StateFRPDTO stateFRPDTO) {
		log.info("Start - StateServiceImpl.edit - StateFRPDTO: {}", stateFRPDTO);

		this.countryProcessor.exists(stateFRPDTO.getId());

		State state = this.mapper.map(stateFRPDTO, State.class);
		state = this.stateProcessor.merge(state);

		state = this.stateRepository.save(state);
		stateFRPDTO = this.mapper.map(state, StateFRPDTO.class);

		log.info("End - StateServiceImpl.edit - StateFRPDTO: {}", stateFRPDTO);
		return stateFRPDTO;
	}

	@Override
	public StateHRDTO remove(Long id) {
		log.info("Start - StateServiceImpl.remove - Id: {}", id);

		State state = this.stateProcessor.exists(id);
		this.stateRepository.deleteById(id);

		StateHRDTO stateHRDTO = this.mapper.map(state, StateHRDTO.class);

		log.info("End - StateServiceImpl.remove - StateHRDTO: {}", stateHRDTO);
		return stateHRDTO;
	}
}
