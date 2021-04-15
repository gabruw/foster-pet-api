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
import com.foster.pet.dto.state.StateFRDTO;
import com.foster.pet.dto.state.StateHRDTO;
import com.foster.pet.dto.state.StatePDTO;
import com.foster.pet.dto.state.StateRDTO;
import com.foster.pet.entity.Country;
import com.foster.pet.entity.State;
import com.foster.pet.exception.country.CountryNotFoundException;
import com.foster.pet.exception.state.StateAlreadyExistsException;
import com.foster.pet.exception.state.StateNotFoundException;
import com.foster.pet.repository.CountryRepository;
import com.foster.pet.repository.StateRepository;
import com.foster.pet.service.StateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public Page<StateHRDTO> findAll(Pageable pageable) {
		log.info("Start - StateServiceImpl.findAll - Pageable: {}", pageable);

		Page<State> states = this.stateRepository.findAll(pageable);
		Page<StateHRDTO> fltStates = states.map(state -> this.mapper.map(state, StateHRDTO.class));

		log.info("End - StateServiceImpl.findAll - Page<StateHRDTO>: {}", fltStates.toString());
		return fltStates;
	}

	@Override
	public List<OptionDTO<Long>> findOptions(Long countryId) {
		log.info("Start - StateServiceImpl.findOptions");

		Optional<Country> optCountry = this.countryRepository.findById(countryId);
		if (optCountry.isEmpty()) {
			log.error("CountryNotFoundException - CountryId: {}", countryId);
			throw new CountryNotFoundException();
		}

		List<OptionDTO<Long>> options = optCountry.get().getState().stream()
				.map(state -> new OptionDTO<Long>(state.getName(), state.getId())).collect(Collectors.toList());

		log.info("End - StateServiceImpl.findOptions - List<OptionDTO<Long>>: {}", options.toString());
		return options;
	}

	@Override
	public StateFRDTO findById(Long id) {
		log.info("Start - StateServiceImpl.findById - Id: {}", id);

		Optional<State> optState = this.stateRepository.findById(id);
		if (optState.isEmpty()) {
			log.error("StateNotFoundException - Id: {}", id);
			throw new StateNotFoundException();
		}

		StateFRDTO state = this.mapper.map(optState.get(), StateFRDTO.class);

		log.info("End - StateServiceImpl.findById - StateFRDTO {}", state.toString());
		return state;
	}

	@Override
	public StateFRDTO findByName(String name) {
		log.info("Start - StateServiceImpl.findByName - Name: {}", name);

		Optional<State> optState = this.stateRepository.findByName(name);
		if (optState.isEmpty()) {
			log.error("StateNotFoundException - Name: {}", name);
			throw new StateNotFoundException();
		}

		StateFRDTO state = this.mapper.map(optState.get(), StateFRDTO.class);

		log.info("End - StateServiceImpl.findByName - StateFRDTO: {}", state.toString());
		return state;
	}

	@Override
	public StateRDTO persist(StatePDTO statePDTO) {
		log.info("Start - StateServiceImpl.persist - StatePDTO: {}", statePDTO.toString());

		Optional<State> optState = this.stateRepository.findByName(statePDTO.getName());
		if (optState.isPresent()) {
			log.error("StateAlreadyExistsException - Name: {}", statePDTO.getName());
			throw new StateAlreadyExistsException();
		}

		State state = this.mapper.map(statePDTO, State.class);
		state = this.stateRepository.save(state);

		StateRDTO stateRDTO = this.mapper.map(state, StateRDTO.class);

		log.info("End - StateServiceImpl.persist - StateRDTO: {}", stateRDTO.toString());
		return stateRDTO;
	}

	@Override
	public StateHRDTO deleteById(Long id) {
		log.info("Start - StateServiceImpl.deleteById - Id: {}", id);

		Optional<State> optState = this.stateRepository.findById(id);
		if (optState.isEmpty()) {
			log.error("StateNotFoundException - Id: {}", id);
			throw new StateNotFoundException();
		}

		this.stateRepository.deleteById(id);
		StateHRDTO state = this.mapper.map(optState.get(), StateHRDTO.class);

		log.info("End - StateServiceImpl.deleteById - StateHRDTO: {}", state.toString());
		return state;
	}
}
