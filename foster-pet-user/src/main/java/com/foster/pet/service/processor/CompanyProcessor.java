package com.foster.pet.service.processor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foster.pet.entity.Company;
import com.foster.pet.exception.company.CompanyAlreadyExistsException;
import com.foster.pet.repository.CompanyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CompanyProcessor {

	@Autowired
	private CompanyRepository companyRepository;

	public void validateToPersist(Company company) {
		log.info("Start - CompanyProcessor.validateToPersist - Company {}", company.toString());

		Optional<Company> optCompany = this.companyRepository.findByCnpj(company.getCnpj());
		if (optCompany.isPresent()) {
			log.error("CompanyAlreadyExistsException - CNPJ: {}", company.getCnpj());
			throw new CompanyAlreadyExistsException();
		}

		log.info("End - CompanyProcessor.validateToPersist - Company {}", company.toString());
	}
}
