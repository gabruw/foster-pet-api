package com.foster.pet.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.OptionDTO;
import com.foster.pet.dto.state.StateFPDTO;
import com.foster.pet.dto.state.StateFRDTO;
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
	public StateFRDTO findById(Long id) {
		log.info("Start - StateServiceImpl.findById - Id: {}", id);

		State state = this.stateProcessor.exists(id);
		StateFRDTO stateFRDTO = this.mapper.map(state, StateFRDTO.class);

		log.info("End - StateServiceImpl.findById - StateFRDTO: {}", stateFRDTO);
		return stateFRDTO;
	}

	@Override
	public StateFRDTO findByName(String name) {
		log.info("Start - StateServiceImpl.findByName - Name: {}", name);

		State state = this.stateProcessor.exists(name);
		StateFRDTO stateFRDTO = this.mapper.map(state, StateFRDTO.class);

		log.info("End - StateServiceImpl.findByName - StateFRDTO: {}", stateFRDTO);
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
	public StateFPDTO edit(StateFPDTO stateFPDTO) {
		log.info("Start - StateServiceImpl.edit - StateFPDTO: {}", stateFPDTO);

		this.stateProcessor.exists(stateFPDTO.getId());

		State state = this.mapper.map(stateFPDTO, State.class);
		state = this.stateProcessor.merge(state);

		state = this.stateRepository.save(state);
		stateFPDTO = this.mapper.map(state, StateFPDTO.class);

		log.info("End - StateServiceImpl.edit - StateFPDTO: {}", stateFPDTO);
		return stateFPDTO;
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
