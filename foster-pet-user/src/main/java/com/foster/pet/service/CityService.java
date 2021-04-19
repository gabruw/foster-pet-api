package com.foster.pet.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foster.pet.dto.OptionDTO;
import com.foster.pet.dto.city.CityFPDTO;
import com.foster.pet.dto.city.CityFRDTO;
import com.foster.pet.dto.city.CityHRDTO;
import com.foster.pet.dto.city.CityPDTO;
import com.foster.pet.dto.city.CityRDTO;

public interface CityService {

	Page<CityHRDTO> findAll(Pageable pageable);

	List<OptionDTO<Long>> findOptions(Long stateId);

	CityFRDTO findById(Long id);

	CityFRDTO findByName(String name);

	CityRDTO register(CityPDTO cityPDTO);

	CityFPDTO edit(CityFPDTO cityFPDTO);

	CityHRDTO remove(Long id);
}
