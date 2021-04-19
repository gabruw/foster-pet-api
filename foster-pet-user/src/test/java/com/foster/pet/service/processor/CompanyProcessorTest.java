package com.foster.pet.service.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.foster.pet.entity.Company;
import com.foster.pet.exception.company.CompanyAlreadyExistsException;
import com.foster.pet.repository.CompanyRepository;
import com.foster.pet.service.prcr.CompanyProcessor;

import properties.company.CompanyInstance;
import properties.company.CompanyProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Processor - Company")
public class CompanyProcessorTest extends CompanyProperties {

	@MockBean
	private CompanyRepository companyRepository;

	@Autowired
	private CompanyProcessor companyProcessor;

	@Mock
	private Company company;

	@BeforeEach
	public void init() {
		this.company = CompanyInstance.instace();
	}

	@Test
	@DisplayName("Validade company to persist")
	public void validateToPersist() {
		this.companyProcessor.exists(this.company);
		verify(this.companyRepository, times(1)).findByCnpj(CNPJ);
	}

	@Test
	@DisplayName("Validade company to persist with a company already existing")
	public void persistWithAlreadyExistsCnpj() {
		CompanyAlreadyExistsException exception = assertThrows(CompanyAlreadyExistsException.class, () -> {
			when(this.companyRepository.findByCnpj(CNPJ)).thenReturn(Optional.of(this.company));

			this.companyProcessor.exists(this.company);
		});

		assertEquals(ErrorCode.COMPANY_ALREADY_EXISTS.getMessage(), exception.getMessage());
	}
}
