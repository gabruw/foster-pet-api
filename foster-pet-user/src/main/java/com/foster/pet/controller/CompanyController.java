package com.foster.pet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foster.pet.dto.authentication.AuthenticationDTO;
import com.foster.pet.dto.company.CompanyRDTO;
import com.foster.pet.entity.Authentication;
import com.foster.pet.entity.Company;
import com.foster.pet.service.AuthenticationService;
import com.foster.pet.service.CompanyService;
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
	private CompanyService companyService;
	
	@Autowired
	private CompanyProcessor companyProcessor;

	@Autowired
	private AuthenticationService authenticationService;
	

	@GetMapping
	@Cacheable("company")
	public ResponseEntity<Response<List<CompanyRDTO>>> getAll() {
		log.info("Start - CompanyController.getAll");
		Response<List<CompanyRDTO>> response = new Response<List<CompanyRDTO>>();

		List<CompanyRDTO> companyDTOS = this.companyService.findAll();
		response.setData(companyDTOS);

		log.info("End - CompanyController.getAll - List<CompanyDTO>: {}", companyDTOS.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("company")
	@GetMapping(params = "id")
	public ResponseEntity<Response<Company>> getById(@RequestParam Long id) {
		log.info("Start - CompanyController.getById - Id: {}", id);
		Response<Company> response = new Response<Company>();

		Company company = this.companyService.findById(id);
		response.setData(company);

		log.info("End - CompanyController.getById - Company: {}", company.toString());
		return ResponseEntity.ok(response);
	}

	@Cacheable("company")
	@GetMapping(params = "cnpj")
	public ResponseEntity<Response<Company>> getByCnpj(@RequestParam String cnpj) {
		log.info("Start - CompanyController.getByCnpj - CNPJ: {}", cnpj);
		Response<Company> response = new Response<Company>();

		Company company = this.companyService.findByCnpj(cnpj);
		response.setData(company);

		log.info("End - CompanyController.getByCnpj - Company: {}", company.toString());
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<Authentication>> register(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
		log.info("Start - PersonController.persist - AuthenticationDTO: {}", authenticationDTO.toString());
		Response<Authentication> response = new Response<Authentication>();

		this.companyProcessor.validateToPersist(authenticationDTO.getCompany());

		Authentication authentication = this.authenticationService.persist(authenticationDTO);
		response.setData(authentication);

		log.info("End - PersonController.persist - Authentication: {}", authentication.toString());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(params = "id")
	public ResponseEntity<Response<CompanyRDTO>> remove(@RequestParam Long id) {
		log.info("Start - CompanyController.remove - Id: {}", id);
		Response<CompanyRDTO> response = new Response<CompanyRDTO>();

		CompanyRDTO companyDTO = this.companyService.deleteById(id);
		response.setData(companyDTO);

		log.info("End - CompanyController.remove - Company: {}", companyDTO.toString());
		return ResponseEntity.ok(response);
	}
}
