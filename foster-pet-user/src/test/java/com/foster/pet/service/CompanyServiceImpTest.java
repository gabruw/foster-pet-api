package com.foster.pet.service;

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
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;
import com.foster.pet.dto.CompanyDTO;
import com.foster.pet.entity.Company;
import com.foster.pet.exception.CompanyNotFoundException;
import com.foster.pet.properties.company.CompanyInstance;
import com.foster.pet.properties.company.CompanyProperties;
import com.foster.pet.repository.CompanyRepository;

@SpringBootTest
@ActiveProfiles("test")
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
    public void findAll() {
        List<Company> companies = new ArrayList<>();
        companies.add(this.company);

        when(this.companyRepository.findAll()).thenReturn(companies);

        List<CompanyDTO> returnedCompanies = this.companyService.findAll();
        Optional<CompanyDTO> optCompany = returnedCompanies.stream().findFirst();

        assertEquals(CNPJ, optCompany.get().getCnpj());
        assertEquals(COMPANY_NAME, optCompany.get().getCompanyName());
        assertEquals(TRADE_NAME, optCompany.get().getTradeName());
    }

    @Test
    public void findById() {
        when(this.companyRepository.findById(ID)).thenReturn(Optional.of(this.company));

        Company returnedCompany = this.companyService.findById(ID);

        assertEquals(ID, returnedCompany.getId());
        assertEquals(CNPJ, returnedCompany.getCnpj());
        assertEquals(COMPANY_NAME, returnedCompany.getCompanyName());
        assertEquals(TRADE_NAME, returnedCompany.getTradeName());
    }

    @Test
    public void findByIdWithNotFoundCompany() {
        Exception exception = assertThrows(CompanyNotFoundException.class, () -> {
            Long invalidId = 2L;
            when(this.companyRepository.findById(ID)).thenReturn(Optional.of(this.company));

            this.companyService.findById(invalidId);
        });

        assertEquals(ErrorCode.COMPANY_NOT_FOUND.getMessage(), exception.getMessage());
    }

    @Test
    public void findByCnpj() {
        when(this.companyRepository.findByCnpj(CNPJ)).thenReturn(Optional.of(this.company));

        Company returnedCompany = this.companyService.findByCnpj(CNPJ);

        assertEquals(ID, returnedCompany.getId());
        assertEquals(CNPJ, returnedCompany.getCnpj());
        assertEquals(COMPANY_NAME, returnedCompany.getCompanyName());
        assertEquals(TRADE_NAME, returnedCompany.getTradeName());
    }

    @Test
    public void findByCnpjWithNotFoundCompany() {
        Exception exception = assertThrows(CompanyNotFoundException.class, () -> {
            String invalidCnpj = "123.456.789/0123-45";
            when(this.companyRepository.findByCnpj(CNPJ)).thenReturn(Optional.of(this.company));

            this.companyService.findByCnpj(invalidCnpj);
        });

        assertEquals(ErrorCode.COMPANY_NOT_FOUND.getMessage(), exception.getMessage());
    }

    @Test
    public void persist() {
        when(this.companyRepository.save(this.company)).thenReturn(this.company);

        Company returnedCompany = this.companyService.persist(this.company);

        assertEquals(ID, returnedCompany.getId());
        assertEquals(CNPJ, returnedCompany.getCnpj());
        assertEquals(COMPANY_NAME, returnedCompany.getCompanyName());
        assertEquals(TRADE_NAME, returnedCompany.getTradeName());
    }

    @Test
    public void deleteById() {
        doNothing().when(this.companyRepository).deleteById(ID);
        when(this.companyRepository.findById(ID)).thenReturn(Optional.of(this.company));

        CompanyDTO returnedCompanyDTO = this.companyService.deleteById(ID);

        assertEquals(CNPJ, returnedCompanyDTO.getCnpj());
        assertEquals(COMPANY_NAME, returnedCompanyDTO.getCompanyName());
        assertEquals(TRADE_NAME, returnedCompanyDTO.getTradeName());

        verify(this.companyRepository, times(1)).deleteById(ID);
    }

    @Test
    public void deleteByIdWithInvalid() {
        Exception exception = assertThrows(CompanyNotFoundException.class, () -> {
            Long invalidId = 2L;
            when(this.companyRepository.findById(ID)).thenReturn(Optional.of(this.company));

            this.companyService.deleteById(invalidId);
        });

        assertEquals(ErrorCode.COMPANY_NOT_FOUND.getMessage(), exception.getMessage());
    }

}
