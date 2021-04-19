package com.foster.pet.service.prcr;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foster.pet.entity.Company;
import com.foster.pet.exception.company.CompanyAlreadyExistsException;
import com.foster.pet.exception.company.CompanyNotFoundException;
import com.foster.pet.repository.CompanyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CompanyProcessor {

	@Autowired
	private CompanyRepository companyRepository;

	public Company exists(Long id) {
		log.info("Start - CompanyProcessor.exists - Id {}", id);

		Optional<Company> optCompany = this.companyRepository.findById(id);
		if (optCompany.isEmpty()) {
			log.error("CompanyNotFoundException - Id: {}", id);
			throw new CompanyNotFoundException();
		}

		log.info("End - CompanyProcessor.exists - Company {}", optCompany.get());
		return optCompany.get();
	}

	public Company exists(String cnpj) {
		log.info("Start - CompanyProcessor.exists - CNPJ {}", cnpj);

		Optional<Company> optCompany = this.companyRepository.findByCnpj(cnpj);
		if (optCompany.isPresent()) {
			log.error("CompanyAlreadyExistsException - CNPJ: {}", cnpj);
			throw new CompanyAlreadyExistsException();
		}

		log.info("End - CompanyProcessor.exists - Company {}", optCompany.get());
		return optCompany.get();
	}
}
