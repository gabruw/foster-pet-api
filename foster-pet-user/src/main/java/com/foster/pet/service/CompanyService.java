package com.foster.pet.service;

import java.util.List;

import com.foster.pet.dto.company.CompanyRDTO;
import com.foster.pet.entity.Company;

public interface CompanyService {

    List<CompanyRDTO> findAll();

    Company findById(Long id);

    Company findByCnpj(String cnpj);

    CompanyRDTO deleteById(Long id);
}
