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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foster.pet.dto.OptionDTO;
import com.foster.pet.dto.city.CityFPDTO;
import com.foster.pet.dto.city.CityFRDTO;
import com.foster.pet.dto.city.CityHRDTO;
import com.foster.pet.dto.city.CityPDTO;
import com.foster.pet.dto.city.CityRDTO;
import com.foster.pet.service.CityService;
import com.foster.pet.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/city")
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping
	@Cacheable("city")
	public ResponseEntity<Response<Page<CityHRDTO>>> findAll(Pageable pageable) {
		log.info("Start - CityController.findAll");
		Response<Page<CityHRDTO>> response = new Response<>();

		Page<CityHRDTO> cities = this.cityService.findAll(pageable);
		response.setData(cities);

		log.info("End - CityController.findAll - Page<CityHRDTO>: {}", cities.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("city")
	@GetMapping(params = "stateId")
	public ResponseEntity<Response<List<OptionDTO<Long>>>> findOptions(@RequestParam Long stateId) {
		log.info("Start - CityController.findOptions - StateId: {}", stateId);
		Response<List<OptionDTO<Long>>> response = new Response<>();

		List<OptionDTO<Long>> options = this.cityService.findOptions(stateId);
		response.setData(options);

		log.info("End - CityController.findOptions - List<OptionDTO<Long>>: {}", options.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("city")
	@GetMapping(params = "id")
	public ResponseEntity<Response<CityFRDTO>> findById(@RequestParam Long id) {
		log.info("Start - CityController.findById - Id: {}", id);
		Response<CityFRDTO> response = new Response<>();

		CityFRDTO city = this.cityService.findById(id);
		response.setData(city);

		log.info("End - CityController.findById - CityFRDTO: {}", city.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("city")
	@GetMapping(params = "name")
	public ResponseEntity<Response<CityFRDTO>> findByName(@RequestParam String name) {
		log.info("Start - CityController.findByName - Name: {}", name);
		Response<CityFRDTO> response = new Response<>();

		CityFRDTO city = this.cityService.findByName(name);
		response.setData(city);

		log.info("End - CityController.findByName - CityFRDTO: {}", city.toString());
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<CityRDTO>> register(@RequestBody @Valid CityPDTO cityPDTO) {
		log.info("Start - CityController.register - CityPDTO: {}", cityPDTO.toString());
		Response<CityRDTO> response = new Response<>();

		CityRDTO city = this.cityService.register(cityPDTO);
		response.setData(city);

		log.info("End - CityController.register - CityRDTO: {}", city.toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping
	public ResponseEntity<Response<CityFPDTO>> edit(@RequestBody @Valid CityFPDTO cityFPDTO) {
		log.info("Start - CityController.edit - CityFPDTO: {}", cityFPDTO.toString());
		Response<CityFPDTO> response = new Response<>();

		CityFPDTO city = this.cityService.edit(cityFPDTO);
		response.setData(city);

		log.info("End - CityController.edit - CityFPDTO: {}", city.toString());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(params = "id")
	public ResponseEntity<Response<CityHRDTO>> remove(@RequestParam Long id) {
		log.info("Start - CityController.remove - Id: {}", id);
		Response<CityHRDTO> response = new Response<>();

		CityHRDTO city = this.cityService.remove(id);
		response.setData(city);

		log.info("End - CityController.remove - CityHRDTO: {}", city.toString());
		return ResponseEntity.ok(response);
	}
}
