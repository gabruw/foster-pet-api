package com.foster.pet.animal.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foster.pet.animal.dto.AnimalDTO;

public interface AnimalService {

	Page<AnimalDTO> findAll(Pageable pageable);
	
	AnimalDTO findById(Long id);
	
	AnimalDTO findByName(String name);
	
	AnimalDTO create(AnimalDTO animalDTO);
	
	AnimalDTO update(AnimalDTO animalDTO);
	
	void delete(Long id);
}
