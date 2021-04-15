package com.foster.pet.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.company.CompanyFRDTO;
import com.foster.pet.dto.company.CompanyRDTO;
import com.foster.pet.entity.Company;
import com.foster.pet.exception.company.CompanyNotFoundException;
import com.foster.pet.repository.CompanyRepository;
import com.foster.pet.service.CompanyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Page<CompanyRDTO> findAll(Pageable pageable) {
		log.info("Start - CompanyServiceImpl.findAll");

		Page<Company> companies = this.companyRepository.findAll(pageable);
		Page<CompanyRDTO> fltCompanies = companies.map(state -> this.mapper.map(state, CompanyRDTO.class));

		log.info("End - CompanyServiceImpl.findAll - Page<CompanyRDTO>: {}", fltCompanies.toString());
		return fltCompanies;
	}

	@Override
	public CompanyFRDTO findById(Long id) {
		log.info("Start - CompanyServiceImpl.findById - Id: {}", id);

		Optional<Company> optCompany = this.companyRepository.findById(id);
		if (optCompany.isEmpty()) {
			log.error("CompanyNotFoundException - Id: {}", id);
			throw new CompanyNotFoundException();
		}

		CompanyFRDTO company = this.mapper.map(optCompany.get(), CompanyFRDTO.class);

		log.info("End - CompanyServiceImpl.findById - CompanyFRDTO {}", company.toString());
		return company;
	}

	@Override
	public CompanyFRDTO findByCnpj(String cnpj) {
		log.info("Start - CompanyServiceImpl.findByCpf - CNPJ: {}", cnpj);

		Optional<Company> optCompany = this.companyRepository.findByCnpj(cnpj);
		if (optCompany.isEmpty()) {
			log.error("CompanyNotFoundException - CNPJ: {}", cnpj);
			throw new CompanyNotFoundException();
		}

		CompanyFRDTO company = this.mapper.map(optCompany.get(), CompanyFRDTO.class);

		log.info("End - CompanyServiceImpl.findByCnpj - CompanyFRDTO {}", company.toString());
		return company;
	}

	@Override
	public CompanyRDTO deleteById(Long id) {
		log.info("Start - CompanyServiceImpl.deleteById - Id: {}", id);

		Optional<Company> optCompany = this.companyRepository.findById(id);
		if (optCompany.isEmpty()) {
			log.error("CompanyNotFoundException - Id: {}", id);
			throw new CompanyNotFoundException();
		}

		this.companyRepository.deleteById(id);
		CompanyRDTO company = this.mapper.map(optCompany.get(), CompanyRDTO.class);

		log.info("End - CompanyServiceImpl.deleteById - CompanyRDTO {}", company.toString());
		return company;
	}
}
