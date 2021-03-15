package com.foster.pet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foster.pet.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	@Transactional(readOnly = true)
	Optional<Person> findByCpf(String cpf);

	@Transactional
	void deleteById(Long Id);
}
