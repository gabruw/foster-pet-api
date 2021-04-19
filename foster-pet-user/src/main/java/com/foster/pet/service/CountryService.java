package com.foster.pet.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foster.pet.dto.OptionDTO;
import com.foster.pet.dto.country.CountryFRPDTO;
import com.foster.pet.dto.country.CountryRPDTO;

public interface CountryService {

	Page<CountryRPDTO> findAll(Pageable pageable);

	List<OptionDTO<Long>> findOptions();

	CountryFRPDTO findById(Long id);

	CountryFRPDTO findByName(String name);

	CountryRPDTO register(CountryRPDTO countryRPDTO);

	CountryFRPDTO edit(CountryFRPDTO countryFRPDTO);

	CountryRPDTO remove(Long id);
}
