package com.foster.pet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foster.pet.dto.OptionDTO;
import com.foster.pet.dto.state.StateFRDTO;
import com.foster.pet.dto.state.StateHRDTO;
import com.foster.pet.dto.state.StatePDTO;
import com.foster.pet.dto.state.StateRDTO;
import com.foster.pet.service.StateService;
import com.foster.pet.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/state")
public class StateController {

	@Autowired
	private StateService stateService;

	@GetMapping
	@Cacheable("state")
	public ResponseEntity<Response<Page<StateHRDTO>>> findAll(Pageable pageable) {
		log.info("Start - StateController.findAll");
		Response<Page<StateHRDTO>> response = new Response<>();

		Page<StateHRDTO> states = this.stateService.findAll(pageable);
		response.setData(states);

		log.info("End - StateController.findAll - Page<StateHRDTO>: {}", states.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("state")
	@GetMapping(params = "countryId")
	public ResponseEntity<Response<List<OptionDTO<Long>>>> findOptions(@RequestParam Long countryId) {
		log.info("Start - StateController.findOptions - CountryId: {}", countryId);
		Response<List<OptionDTO<Long>>> response = new Response<>();

		List<OptionDTO<Long>> options = this.stateService.findOptions(countryId);
		response.setData(options);

		log.info("End - StateController.findOptions - <List<OptionDTO<Long>>>: {}", options.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("state")
	@GetMapping(params = "id")
	public ResponseEntity<Response<StateFRDTO>> findById(@RequestParam Long id) {
		log.info("Start - StateController.findById - Id: {}", id);
		Response<StateFRDTO> response = new Response<>();

		StateFRDTO state = this.stateService.findById(id);
		response.setData(state);

		log.info("End - StateController.findById - StateFRDTO: {}", state.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("state")
	@GetMapping(params = "name")
	public ResponseEntity<Response<StateFRDTO>> findByName(@RequestParam String name) {
		log.info("Start - StateController.findByName - Name: {}", name);
		Response<StateFRDTO> response = new Response<>();

		StateFRDTO state = this.stateService.findByName(name);
		response.setData(state);

		log.info("End - StateController.findByName - StateFRDTO: {}", state.toString());
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<StateRDTO>> register(@RequestBody @Valid StatePDTO statePDTO) {
		log.info("Start - StateController.register - StatePDTO: {}", statePDTO.toString());
		Response<StateRDTO> response = new Response<>();

		StateRDTO state = this.stateService.persist(statePDTO);
		response.setData(state);

		log.info("End - StateController.register - StateRDTO: {}", state.toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@DeleteMapping(params = "id")
	public ResponseEntity<Response<StateHRDTO>> remove(@RequestParam Long id) {
		log.info("Start - StateController.remove - Id: {}", id);
		Response<StateHRDTO> response = new Response<>();

		StateHRDTO state = this.stateService.deleteById(id);
		response.setData(state);

		log.info("End - StateController.remove - StateHRDTO: {}", state.toString());
		return ResponseEntity.ok(response);
	}
}
