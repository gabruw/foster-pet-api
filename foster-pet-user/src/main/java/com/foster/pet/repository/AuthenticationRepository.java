package com.foster.pet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foster.pet.entity.Authentication;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

	@Transactional(readOnly = true)
	Optional<Authentication> findByEmail(String email);
}
