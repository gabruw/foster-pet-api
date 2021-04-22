package com.foster.pet.animal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foster.pet.animal.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
