package com.foster.pet.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.foster.pet.dto.CompanyDTO;
import com.foster.pet.entity.Company;
import com.foster.pet.properties.company.CompanyInstance;
import com.foster.pet.properties.company.CompanyProperties;
import com.foster.pet.service.CompanyService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CompanyControllerTest extends CompanyProperties {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModelMapper mapper;

    @MockBean
    private CompanyService companyService;

    @Mock
    private Company company;

    @Mock
    private CompanyDTO companyDTO;

    private final String BASE_URL = "/company";

    @BeforeEach
    public void init() {
        this.company = CompanyInstance.instace();
        this.companyDTO = this.mapper.map(this.company, CompanyDTO.class);
    }

    @Test
    public void getAll() throws Exception {
        List<CompanyDTO> companyDTOS = new ArrayList<>();
        companyDTOS.add(this.companyDTO);

        when(this.companyService.findAll()).thenReturn(companyDTOS);

        this.mockMvc.perform(get(BASE_URL)).andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].cnpj", equalTo(CNPJ)))
                .andExpect(jsonPath("$.data[0].tradName", equalTo(TRADE_NAME)))
                .andExpect(jsonPath("$.data[0].companyName", equalTo(COMPANY_NAME)))
                .andExpect(jsonPath("$.errors").isEmpty());
    }

    @Test
    public void findById() throws Exception {
        when(this.companyService.findById(ID)).thenReturn(this.company);

        this.mockMvc.perform(get(BASE_URL).param("id", String.valueOf(ID)))
                .andExpect(jsonPath("$.data[0].cnpj", equalTo(CNPJ)))
                .andExpect(jsonPath("$.data[0].tradName", equalTo(TRADE_NAME)))
                .andExpect(jsonPath("$.data[0].companyName", equalTo(COMPANY_NAME)))
                .andExpect(jsonPath("$.errors").isEmpty());
    }

    @Test
    public void getByCpf() throws Exception {
        when(this.companyService.findByCnpj(CNPJ)).thenReturn(this.company);

        this.mockMvc.perform(get(BASE_URL).param("cnpj", CNPJ))
                .andExpect(jsonPath("$.data[0].cnpj", equalTo(CNPJ)))
                .andExpect(jsonPath("$.data[0].tradName", equalTo(TRADE_NAME)))
                .andExpect(jsonPath("$.data[0].companyName", equalTo(COMPANY_NAME)))
                .andExpect(jsonPath("$.errors").isEmpty());
    }

    @Test
    public void remove() throws Exception {
        when(this.companyService.deleteById(ID)).thenReturn(this.companyDTO);

        this.mockMvc.perform(delete(BASE_URL).param("id", String.valueOf(ID)))
                .andExpect(jsonPath("$.data[0].cnpj", equalTo(CNPJ)))
                .andExpect(jsonPath("$.data[0].tradName", equalTo(TRADE_NAME)))
                .andExpect(jsonPath("$.data[0].companyName", equalTo(COMPANY_NAME)))
                .andExpect(jsonPath("$.errors").isEmpty());
    }
}
