package com.foster.pet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import com.foster.pet.service.CompanyService;
import com.foster.pet.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

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
	public ResponseEntity<Response<AuthenticationCompanyPDTO>> register(
			@RequestBody @Valid AuthenticationCompanyPDTO authenticationCompanyPDTO) {
		log.info("Start - CompanyController.register - AuthenticationCompanyPDTO: {}",
				authenticationCompanyPDTO.toString());
		Response<AuthenticationCompanyPDTO> response = new Response<>();

		authenticationCompanyPDTO = this.companyService.register(authenticationCompanyPDTO);
		response.setData(authenticationCompanyPDTO);

		log.info("End - CompanyController.register - AuthenticationCompanyPDTO: {}",
				authenticationCompanyPDTO.toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@DeleteMapping(params = "id")
	public ResponseEntity<Response<CompanyRDTO>> remove(@RequestParam Long id) {
		log.info("Start - CompanyController.remove - Id: {}", id);
		Response<CompanyRDTO> response = new Response<>();

		CompanyRDTO company = this.companyService.remove(id);
		response.setData(company);

		log.info("End - CompanyController.remove - CompanyRDTO: {}", company.toString());
		return ResponseEntity.ok(response);
	}
}
