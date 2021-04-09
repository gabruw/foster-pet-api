package com.foster.pet.service;

import java.util.List;

import com.foster.pet.dto.country.CountryDTO;
import com.foster.pet.entity.Country;

public interface CountryService {

	List<CountryDTO> findAll();

	Country findById(Long id);

	Country findByName(String name);

	Country persist(Country country);
	
	CountryDTO deleteById(Long id);
}
