package com.foster.pet.service;

import java.util.List;

import com.foster.pet.dto.CompanyDTO;
import com.foster.pet.entity.Company;

public interface CompanyService {

    List<CompanyDTO> findAll();

    Company findById(Long id);

    Company findByCnpj(String cnpj);

    Company persist(Company company);

    CompanyDTO deleteById(Long id);
}
