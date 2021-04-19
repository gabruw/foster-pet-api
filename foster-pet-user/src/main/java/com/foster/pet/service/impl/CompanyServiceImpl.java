package com.foster.pet.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.authentication.AuthenticationCompanyPDTO;
import com.foster.pet.dto.company.CompanyFRDTO;
import com.foster.pet.dto.company.CompanyRDTO;
import com.foster.pet.entity.Address;
import com.foster.pet.entity.Authentication;
import com.foster.pet.entity.Company;
import com.foster.pet.repository.CompanyRepository;
import com.foster.pet.service.AuthenticationService;
import com.foster.pet.service.CompanyService;
import com.foster.pet.service.prcr.AddressProcessor;
import com.foster.pet.service.prcr.CompanyProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CompanyProcessor companyProcessor;

	@Autowired
	private AddressProcessor addressProcessor;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private AuthenticationService authenticationService;

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

		Company company = this.companyProcessor.exists(id);
		CompanyFRDTO companyFRDTO = this.mapper.map(company, CompanyFRDTO.class);

		log.info("End - CompanyServiceImpl.findById - CompanyFRDTO: {}", companyFRDTO.toString());
		return companyFRDTO;
	}

	@Override
	public CompanyFRDTO findByCnpj(String cnpj) {
		log.info("Start - CompanyServiceImpl.findByCpf - CNPJ: {}", cnpj);

		Company company = this.companyProcessor.exists(cnpj);
		CompanyFRDTO companyFRDTO = this.mapper.map(company, CompanyFRDTO.class);

		log.info("End - CompanyServiceImpl.findByCnpj - CompanyFRDTO: {}", companyFRDTO.toString());
		return companyFRDTO;
	}

	@Override
	public AuthenticationCompanyPDTO register(AuthenticationCompanyPDTO authenticationCompanyPDTO) {
		log.info("Start - CompanyServiceImpl.register - AuthenticationCompanyPDTO: {}",
				authenticationCompanyPDTO.toString());

		Authentication authentication = this.mapper.map(authenticationCompanyPDTO, Authentication.class);
		this.companyProcessor.exists(authentication.getCompany().getCnpj());

		List<Address> validatedAddresses = this.addressProcessor.validade(authentication.getCompany().getAddresses());
		authentication.getCompany().setAddresses(validatedAddresses);

		authentication = this.authenticationService.register(authentication);
		authenticationCompanyPDTO = this.mapper.map(authentication, AuthenticationCompanyPDTO.class);

		log.info("End - CompanyServiceImpl.register - AuthenticationCompanyPDTO: {}",
				authenticationCompanyPDTO.toString());
		return authenticationCompanyPDTO;
	}

	@Override
	public CompanyRDTO remove(Long id) {
		log.info("Start - CompanyServiceImpl.remove - Id: {}", id);

		Company company = this.companyProcessor.exists(id);
		this.companyRepository.deleteById(id);

		CompanyRDTO companyRDTO = this.mapper.map(company, CompanyRDTO.class);

		log.info("End - CompanyServiceImpl.remove - CompanyRDTO: {}", companyRDTO.toString());
		return companyRDTO;
	}
}
