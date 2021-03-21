package com.foster.pet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cache.annotation.Cacheable;

import com.foster.pet.dto.CompanyDTO;
import com.foster.pet.entity.Company;
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
    public ResponseEntity<Response<List<CompanyDTO>>> getAll() {
        log.info("Start - CompanyController.findAll");
        Response<List<CompanyDTO>> response = new Response<List<CompanyDTO>>();

        List<CompanyDTO> companyDTOS = this.companyService.findAll();
        response.setData(companyDTOS);

        log.debug("End - CompanyController.findAll - List<CompanyDTO>: {}", companyDTOS.toString());
        return ResponseEntity.ok(response);
    }

    @Cacheable("company")
    @GetMapping(params = "id")
    public ResponseEntity<Response<Company>> getById(@RequestParam Long id) {
        log.info("Start - CompanyController.findById - Id: {}", id);
        Response<Company> response = new Response<Company>();

        Company company = this.companyService.findById(id);
        response.setData(company);

        log.debug("End - CompanyController.findById - Company: {}", company.toString());
        return ResponseEntity.ok(response);
    }

    @Cacheable("company")
    @GetMapping(params = "cnpj")
    public ResponseEntity<Response<Company>> getByCnpj(@RequestParam String cnpj) {
        log.info("Start - CompanyController.findByCnpj - Id: {}", cnpj);
        Response<Company> response = new Response<Company>();

        Company company = this.companyService.findByCnpj(cnpj);
        response.setData(company);

        log.debug("End - CompanyController.findByCnpj - Company: {}", company.toString());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Response<CompanyDTO>> remove(@RequestParam Long id) {
        log.info("Start - CompanyController.findById - Id: {}", id);
        Response<CompanyDTO> response = new Response<CompanyDTO>();

        CompanyDTO companyDTO = this.companyService.deleteById(id);
        response.setData(companyDTO);

        log.debug("End - CompanyController.findById - Company: {}", companyDTO.toString());
        return ResponseEntity.ok(response);
    }

}
