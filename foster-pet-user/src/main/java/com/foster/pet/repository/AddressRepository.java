package com.foster.pet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foster.pet.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	@Transactional(readOnly = true)
	List<Address> findByCep(String cep);
}
