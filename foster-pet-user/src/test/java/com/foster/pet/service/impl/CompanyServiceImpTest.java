package com.foster.pet.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;
import com.foster.pet.dto.company.CompanyRDTO;
import com.foster.pet.entity.Company;
import com.foster.pet.exception.company.CompanyNotFoundException;
import com.foster.pet.repository.CompanyRepository;
import com.foster.pet.service.CompanyService;

import properties.company.CompanyInstance;
import properties.company.CompanyProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("ServiceImpl - Company")
public class CompanyServiceImpTest extends CompanyProperties {

	@MockBean
	private CompanyRepository companyRepository;

	@Autowired
	private CompanyService companyService;

	@Mock
	private Company company;

	@BeforeEach
	public void init() {
		this.company = CompanyInstance.instace();
	}

	@Test
	@DisplayName("Find all companies")
	public void findAll() {
		List<Company> companies = new ArrayList<>();
		companies.add(this.company);

		when(this.companyRepository.findAll()).thenReturn(companies);

		List<CompanyRDTO> returnedCompanies = this.companyService.findAll();
		Optional<CompanyRDTO> optCompany = returnedCompanies.stream().findFirst();

		assertEquals(CNPJ, optCompany.get().getCnpj());
		assertEquals(COMPANY_NAME, optCompany.get().getCompanyName());
		assertEquals(TRADE_NAME, optCompany.get().getTradeName());
	}

	@Test
	@DisplayName("Find a company by Id")
	public void findById() {
		when(this.companyRepository.findById(ID)).thenReturn(Optional.of(this.company));

		Company returnedCompany = this.companyService.findById(ID);

		assertEquals(ID, returnedCompany.getId());
		assertEquals(CNPJ, returnedCompany.getCnpj());
		assertEquals(COMPANY_NAME, returnedCompany.getCompanyName());
		assertEquals(TRADE_NAME, returnedCompany.getTradeName());
	}

	@Test
	@DisplayName("Find a company by Id with invalid Id")
	public void findByIdWithNotFoundCompany() {
		CompanyNotFoundException exception = assertThrows(CompanyNotFoundException.class, () -> {
			Long invalidId = 2L;
			when(this.companyRepository.findById(ID)).thenReturn(Optional.of(this.company));

			this.companyService.findById(invalidId);
		});

		assertEquals(ErrorCode.COMPANY_NOT_FOUND.getMessage(), exception.getMessage());
	}

	@Test
	@DisplayName("Find a company by CNPJ")
	public void findByCnpj() {
		when(this.companyRepository.findByCnpj(CNPJ)).thenReturn(Optional.of(this.company));

		Company returnedCompany = this.companyService.findByCnpj(CNPJ);

		assertEquals(ID, returnedCompany.getId());
		assertEquals(CNPJ, returnedCompany.getCnpj());
		assertEquals(COMPANY_NAME, returnedCompany.getCompanyName());
		assertEquals(TRADE_NAME, returnedCompany.getTradeName());
	}

	@Test
	@DisplayName("Find a company by CNPJ with invalid CNPJ")
	public void findByCnpjWithNotFoundCompany() {
		Exception exception = assertThrows(CompanyNotFoundException.class, () -> {
			String invalidCnpj = "123.456.789/0123-45";
			when(this.companyRepository.findByCnpj(CNPJ)).thenReturn(Optional.of(this.company));

			this.companyService.findByCnpj(invalidCnpj);
		});

		assertEquals(ErrorCode.COMPANY_NOT_FOUND.getMessage(), exception.getMessage());
	}

	@Test
	@DisplayName("Delete a company by Id")
	public void deleteById() {
		doNothing().when(this.companyRepository).deleteById(ID);
		when(this.companyRepository.findById(ID)).thenReturn(Optional.of(this.company));

		CompanyRDTO returnedCompanyDTO = this.companyService.remove(ID);

		assertEquals(CNPJ, returnedCompanyDTO.getCnpj());
		assertEquals(COMPANY_NAME, returnedCompanyDTO.getCompanyName());
		assertEquals(TRADE_NAME, returnedCompanyDTO.getTradeName());

		verify(this.companyRepository, times(1)).deleteById(ID);
	}

	@Test
	@DisplayName("Delete a company by Id with invalid Id")
	public void deleteByIdWithInvalid() {
		Exception exception = assertThrows(CompanyNotFoundException.class, () -> {
			Long invalidId = 2L;
			when(this.companyRepository.findById(ID)).thenReturn(Optional.of(this.company));

			this.companyService.remove(invalidId);
		});

		assertEquals(ErrorCode.COMPANY_NOT_FOUND.getMessage(), exception.getMessage());
	}
}
