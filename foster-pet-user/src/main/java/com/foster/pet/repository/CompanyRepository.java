package com.foster.pet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foster.pet.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Transactional(readOnly = true)
    Optional<Company> findByCnpj(String cnpj);

    @Transactional
    void deleteById(Long Id);

}
