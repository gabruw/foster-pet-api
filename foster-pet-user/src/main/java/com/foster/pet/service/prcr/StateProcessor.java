package com.foster.pet.service.prcr;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foster.pet.entity.Country;
import com.foster.pet.entity.State;
import com.foster.pet.exception.state.StateAlreadyExistsException;
import com.foster.pet.exception.state.StateNotChangedException;
import com.foster.pet.exception.state.StateNotFoundException;
import com.foster.pet.repository.StateRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StateProcessor {

	@Autowired
	private CountryProcessor countryProcessor;

	@Autowired
	private StateRepository stateRepository;

	public State exists(Long id) {
		log.info("Start - StateProcessor.exists - Id: {}", id);

		Optional<State> optState = this.stateRepository.findById(id);
		if (optState.isEmpty()) {
			log.error("StateNotFoundException - Id: {}", id);
			throw new StateNotFoundException();
		}

		log.info("End - StateProcessor.exists - State: {}", optState.get());
		return optState.get();
	}

	public State exists(String name) {
		log.info("Start - StateProcessor.exists - Name: {}", name);

		Optional<State> optState = this.stateRepository.findByName(name);
		if (optState.isEmpty()) {
			log.error("StateNotFoundException - Name: {}", name);
			throw new StateNotFoundException();
		}

		log.info("End - StateProcessor.exists - State: {}", optState.get());
		return optState.get();
	}

	public void alreadyExists(String name) {
		log.info("Start - StateProcessor.alreadyExists - Name: {}", name);

		Optional<State> optState = this.stateRepository.findByName(name);
		if (optState.isPresent()) {
			log.error("StateAlreadyExistsException - Name: {}", name);
			throw new StateAlreadyExistsException();
		}

		log.info("End - StateProcessor.alreadyExists");
	}

	public State append(State state) {
		log.info("Start - StateProcessor.append - State: {}", state);

		Country country = this.countryProcessor.exists(state.getCountry().getId());
		state.setCountry(country);

		log.info("End - StateProcessor.append - State: {}", state);
		return state;
	}

	public State merge(State state) {
		log.info("Start - StateProcessor.merge - State: {}", state);

		State original = this.exists(state.getId());
		state.setCity(original.getCity());
		state.setCountry(original.getCountry());
		
		if (Objects.equals(state, original)) {
			log.error("StateNotChangedException - State: {}", state);
			throw new StateNotChangedException();
		}

		log.info("End - StateProcessor.merge - State: {}", state);
		return state;
	}
}
