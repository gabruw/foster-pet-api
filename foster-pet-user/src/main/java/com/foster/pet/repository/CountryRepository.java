package com.foster.pet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foster.pet.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	@Transactional(readOnly = true)
	Optional<Country> findByName(String name);
}
