package com.foster.pet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foster.pet.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	@Transactional(readOnly = true)
	Page<Address> findAllByCep(String cep, Pageable pageable);
}
