package com.foster.pet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foster.pet.dto.authentication.AuthenticationCompanyPDTO;
import com.foster.pet.dto.company.CompanyFRDTO;
import com.foster.pet.dto.company.CompanyRDTO;

public interface CompanyService {

	Page<CompanyRDTO> findAll(Pageable pageable);

	CompanyFRDTO findById(Long id);

	CompanyFRDTO findByCnpj(String cnpj);

	AuthenticationCompanyPDTO register(AuthenticationCompanyPDTO authenticationCompanyPDTO);

	CompanyRDTO remove(Long id);
}
