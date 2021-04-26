package com.foster.pet.animal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foster.pet.animal.service.AnimalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("animal")
public class AnimalController {

	@Autowired
	private AnimalService animalService;
}
