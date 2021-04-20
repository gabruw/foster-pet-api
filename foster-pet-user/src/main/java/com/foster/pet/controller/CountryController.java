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
import com.foster.pet.dto.country.CountryFRPDTO;
import com.foster.pet.dto.country.CountryRPDTO;
import com.foster.pet.service.CountryService;
import com.foster.pet.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/country")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping
	@Cacheable("country")
	public ResponseEntity<Response<Page<CountryRPDTO>>> findAll(Pageable pageable) {
		log.info("Start - CountryController.findAll");
		Response<Page<CountryRPDTO>> response = new Response<>();

		Page<CountryRPDTO> countries = this.countryService.findAll(pageable);
		response.setData(countries);

		log.info("End - CountryController.findAll - Page<CountryRPDTO>: {}", countries.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("country")
	@GetMapping("/options")
	public ResponseEntity<Response<List<OptionDTO<Long>>>> findOptions() {
		log.info("Start - CountryController.findOptions");
		Response<List<OptionDTO<Long>>> response = new Response<>();

		List<OptionDTO<Long>> options = this.countryService.findOptions();
		response.setData(options);

		log.info("End - CountryController.findOptions - <List<OptionDTO<Long>>>: {}", options.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("country")
	@GetMapping(params = "id")
	public ResponseEntity<Response<CountryFRPDTO>> findById(@RequestParam Long id) {
		log.info("Start - CountryController.findById - Id: {}", id);
		Response<CountryFRPDTO> response = new Response<>();

		CountryFRPDTO country = this.countryService.findById(id);
		response.setData(country);

		log.info("End - CountryController.findById - CountryFRPDTO: {}", country.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("country")
	@GetMapping(params = "name")
	public ResponseEntity<Response<CountryFRPDTO>> findByName(@RequestParam String name) {
		log.info("Start - CountryController.findByName - Name: {}", name);
		Response<CountryFRPDTO> response = new Response<>();

		CountryFRPDTO country = this.countryService.findByName(name);
		response.setData(country);

		log.info("End - CountryController.findByName - CountryFRPDTO: {}", country.toString());
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<CountryRPDTO>> register(@RequestBody @Valid CountryRPDTO countryRPDTO) {
		log.info("Start - CountryController.register - CountryRPDTO: {}", countryRPDTO.toString());
		Response<CountryRPDTO> response = new Response<>();

		CountryRPDTO country = this.countryService.register(countryRPDTO);
		response.setData(country);

		log.info("End - CountryController.register - CountryRPDTO: {}", country.toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping
	public ResponseEntity<Response<CountryFRPDTO>> edit(@RequestBody @Valid CountryFRPDTO countryFRPDTO) {
		log.info("Start - CountryController.edit - CountryFRPDTO: {}", countryFRPDTO.toString());
		Response<CountryFRPDTO> response = new Response<>();

		CountryFRPDTO country = this.countryService.edit(countryFRPDTO);
		response.setData(country);

		log.info("End - CountryController.edit - CountryFRPDTO: {}", country.toString());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(params = "id")
	public ResponseEntity<Response<CountryRPDTO>> remove(@RequestParam Long id) {
		log.info("Start - CountryController.remove - Id: {}", id);
		Response<CountryRPDTO> response = new Response<>();

		CountryRPDTO country = this.countryService.remove(id);
		response.setData(country);

		log.info("End - CountryController.remove - CountryRPDTO: {}", country.toString());
		return ResponseEntity.ok(response);
	}
}
