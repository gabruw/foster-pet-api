package com.foster.pet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foster.pet.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	@Transactional(readOnly = true)
	Optional<City> findByName(String name);
}
