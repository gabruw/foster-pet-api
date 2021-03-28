package com.foster.pet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.entity.Company;

import properties.company.CompanyInstance;
import properties.company.CompanyProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Repository - Company")
public class CompanyRepositoryTest extends CompanyProperties {

	@MockBean
	private CompanyRepository companyRepository;

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

		List<Company> returnedCompanies = this.companyRepository.findAll();
		Optional<Company> optCompany = returnedCompanies.stream().findFirst();

		assertEquals(ID, optCompany.get().getId());
		assertEquals(CNPJ, optCompany.get().getCnpj());
		assertEquals(COMPANY_NAME, optCompany.get().getCompanyName());
		assertEquals(TRADE_NAME, optCompany.get().getTradeName());
	}

	@Test
	@DisplayName("Find a company by Id")
	public void findById() {
		when(this.companyRepository.findById(ID)).thenReturn(Optional.of(this.company));

		Optional<Company> returnedCompany = this.companyRepository.findById(ID);

		assertEquals(ID, returnedCompany.get().getId());
		assertEquals(CNPJ, returnedCompany.get().getCnpj());
		assertEquals(COMPANY_NAME, returnedCompany.get().getCompanyName());
		assertEquals(TRADE_NAME, returnedCompany.get().getTradeName());
	}

	@Test
	@DisplayName("Find a company by CNPJ")
	public void findByCnpj() {
		when(this.companyRepository.findByCnpj(CNPJ)).thenReturn(Optional.of(this.company));

		Optional<Company> returnedCompany = this.companyRepository.findByCnpj(CNPJ);

		assertEquals(ID, returnedCompany.get().getId());
		assertEquals(CNPJ, returnedCompany.get().getCnpj());
		assertEquals(COMPANY_NAME, returnedCompany.get().getCompanyName());
		assertEquals(TRADE_NAME, returnedCompany.get().getTradeName());
	}

	@Test
	@DisplayName("Persist a company")
	public void persist() {
		when(this.companyRepository.save(this.company)).thenReturn(this.company);

		Company returnedCompany = this.companyRepository.save(this.company);

		assertEquals(ID, returnedCompany.getId());
		assertEquals(CNPJ, returnedCompany.getCnpj());
		assertEquals(COMPANY_NAME, returnedCompany.getCompanyName());
		assertEquals(TRADE_NAME, returnedCompany.getTradeName());
	}

	@Test
	@DisplayName("Delete a company by Id")
	public void deleteById() {
		doNothing().when(this.companyRepository).deleteById(ID);
		when(this.companyRepository.findById(ID)).thenReturn(Optional.of(this.company));

		this.companyRepository.deleteById(ID);
		verify(this.companyRepository, times(1)).deleteById(ID);
	}
}
