package com.foster.pet.animal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foster.pet.animal.dto.AnimalDTO;
import com.foster.pet.animal.repository.AnimalRepository;
import com.foster.pet.animal.service.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService{
	
	@Autowired
	private AnimalRepository animalRepository;

	@Override
	public Page<AnimalDTO> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public AnimalDTO findById(Long id) {
		return null;
	}

	@Override
	public AnimalDTO findByName(String name) {
		return null;
	}

	@Override
	public AnimalDTO create(AnimalDTO animalDTO) {
		return null;
	}

	@Override
	public AnimalDTO update(AnimalDTO animalDTO) {
		return null;
	}

	@Override
	public void delete(Long id) {
	}
	
}