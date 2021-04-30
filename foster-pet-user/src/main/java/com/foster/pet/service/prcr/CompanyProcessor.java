package com.foster.pet.service.prcr;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foster.pet.entity.Address;
import com.foster.pet.entity.Company;
import com.foster.pet.exception.company.CompanyAlreadyExistsException;
import com.foster.pet.exception.company.CompanyNotChangedException;
import com.foster.pet.exception.company.CompanyNotFoundException;
import com.foster.pet.repository.CompanyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CompanyProcessor {

	@Autowired
	private AddressProcessor addressProcessor;

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
		if (optCompany.isEmpty()) {
			log.error("CompanyNotFoundException - CNPJ: {}", cnpj);
			throw new CompanyNotFoundException();
		}

		log.info("End - CompanyProcessor.exists - Company {}", optCompany.get());
		return optCompany.get();
	}

	public void alreadyExists(String cnpj) {
		log.info("Start - CompanyProcessor.exists - CNPJ: {}", cnpj);

		Optional<Company> optPerson = this.companyRepository.findByCnpj(cnpj);
		if (optPerson.isPresent()) {
			log.error("CompanyAlreadyExistsException - CNPJ: {}", cnpj);
			throw new CompanyAlreadyExistsException();
		}

		log.info("End - CompanyProcessor.exists");
	}

	public Company append(Company company) {
		log.info("Start - CompanyProcessor.append - Company: {}", company);

		List<Address> original = this.addressProcessor.validade(company.getAddresses());
		company.setAddresses(original);

		log.info("End - CompanyProcessor.append - Company: {}", company);
		return company;
	}

	public Company merge(Company company) {
		log.info("Start - CompanyProcessor.merge - Company: {}", company);

		Company original = this.exists(company.getId());
		company.setEmployee(original.getEmployee());
		company.setAddresses(original.getAddresses());
		company.setAuthentication(original.getAuthentication());

		if (Objects.equals(company, original)) {
			log.error("CompanyNotChangedException - Company: {}", company);
			throw new CompanyNotChangedException();
		}

		log.info("End - CompanyProcessor.merge - Company: {}", company);
		return company;
	}
}
