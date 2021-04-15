package com.foster.pet.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foster.pet.dto.authentication.AuthenticationCompanyPDTO;
import com.foster.pet.dto.company.CompanyFRDTO;
import com.foster.pet.dto.company.CompanyRDTO;
import com.foster.pet.entity.Address;
import com.foster.pet.entity.Authentication;
import com.foster.pet.service.AuthenticationService;
import com.foster.pet.service.CompanyService;
import com.foster.pet.service.processor.AddressProcessor;
import com.foster.pet.service.processor.CompanyProcessor;
import com.foster.pet.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CompanyProcessor companyProcessor;

	@Autowired
	private AddressProcessor addressProcessor;

	@Autowired
	private AuthenticationService authenticationService;

	@GetMapping
	@Cacheable("company")
	public ResponseEntity<Response<Page<CompanyRDTO>>> findAll(Pageable pageable) {
		log.info("Start - CompanyController.findAll");
		Response<Page<CompanyRDTO>> response = new Response<>();

		Page<CompanyRDTO> companies = this.companyService.findAll(pageable);
		response.setData(companies);

		log.info("End - CompanyController.findAll - Page<CompanyRDTO>: {}", companies.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("company")
	@GetMapping(params = "id")
	public ResponseEntity<Response<CompanyFRDTO>> findById(@RequestParam Long id) {
		log.info("Start - CompanyController.findById - Id: {}", id);
		Response<CompanyFRDTO> response = new Response<>();

		CompanyFRDTO company = this.companyService.findById(id);
		response.setData(company);

		log.info("End - CompanyController.findById - CompanyFRDTO: {}", company.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("company")
	@GetMapping(params = "cnpj")
	public ResponseEntity<Response<CompanyFRDTO>> findByCnpj(@RequestParam String cnpj) {
		log.info("Start - CompanyController.findByCnpj - CNPJ: {}", cnpj);
		Response<CompanyFRDTO> response = new Response<>();

		CompanyFRDTO company = this.companyService.findByCnpj(cnpj);
		response.setData(company);

		log.info("End - CompanyController.findByCnpj - CompanyFRDTO: {}", company.toString());
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<Authentication>> register(
			@RequestBody @Valid AuthenticationCompanyPDTO authenticationCompanyPDTO) {
		log.info("Start - CompanyController.register - AuthenticationCompanyPDTO: {}",
				authenticationCompanyPDTO.toString());
		Response<Authentication> response = new Response<>();

		Authentication authentication = this.mapper.map(authenticationCompanyPDTO, Authentication.class);
		this.companyProcessor.validateToPersist(authentication.getCompany());

		List<Address> validatedAddresses = this.addressProcessor
				.validateToPersist(authentication.getCompany().getAddresses());
		authentication.getCompany().setAddresses(validatedAddresses);

		authentication = this.authenticationService.persist(authentication);
		response.setData(authentication);

		log.info("End - CompanyController.register - Authentication: {}", authentication.toString());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(params = "id")
	public ResponseEntity<Response<CompanyRDTO>> remove(@RequestParam Long id) {
		log.info("Start - CompanyController.remove - Id: {}", id);
		Response<CompanyRDTO> response = new Response<>();

		CompanyRDTO company = this.companyService.deleteById(id);
		response.setData(company);

		log.info("End - CompanyController.remove - CompanyRDTO: {}", company.toString());
		return ResponseEntity.ok(response);
	}
}
