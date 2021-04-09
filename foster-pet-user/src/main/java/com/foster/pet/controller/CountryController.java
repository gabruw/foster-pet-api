package com.foster.pet.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foster.pet.dto.country.CountryDTO;
import com.foster.pet.entity.Country;
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
	private ModelMapper mapper;

	@Autowired
	private CountryService countryService;

	@GetMapping
	@Cacheable("country")
	public ResponseEntity<Response<List<CountryDTO>>> getAll() {
		log.info("Start - CountryController.getAll");
		Response<List<CountryDTO>> response = new Response<>();

		List<CountryDTO> countries = this.countryService.findAll();
		response.setData(countries);

		log.info("End - CountryController.getAll - List<CountryDTO>: {}", countries.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("country")
	@GetMapping(params = "id")
	public ResponseEntity<Response<Country>> getById(@RequestParam Long id) {
		log.info("Start - CountryController.getById - Id: {}", id);
		Response<Country> response = new Response<>();

		Country country = this.countryService.findById(id);
		response.setData(country);

		log.info("End - CountryController.getById - Country: {}", country.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("country")
	@GetMapping(params = "name")
	public ResponseEntity<Response<Country>> getByName(@RequestParam String name) {
		log.info("Start - CountryController.getByName - Name: {}", name);
		Response<Country> response = new Response<>();

		Country country = this.countryService.findByName(name);
		response.setData(country);

		log.info("End - CountryController.getByName - Country: {}", country.toString());
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<Country>> register(@RequestBody @Valid CountryDTO countryDTO) {
		log.info("Start - CountryController.persist - CountryDTO: {}", countryDTO.toString());
		Response<Country> response = new Response<>();

		Country country = this.mapper.map(countryDTO, Country.class);
		country = this.countryService.persist(country);

		response.setData(country);

		log.info("End - CountryController.persist - Country: {}", country.toString());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(params = "id")
	public ResponseEntity<Response<CountryDTO>> remove(@RequestParam Long id) {
		log.info("Start - CountryController.remove - Id: {}", id);
		Response<CountryDTO> response = new Response<>();

		CountryDTO country = this.countryService.deleteById(id);
		response.setData(country);

		log.info("End - CountryController.remove - CountryDTO: {}", country.toString());
		return ResponseEntity.ok(response);
	}
}
