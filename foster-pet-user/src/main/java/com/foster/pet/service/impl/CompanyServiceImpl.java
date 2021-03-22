package com.foster.pet.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foster.pet.constant.ErrorCode;
import com.foster.pet.dto.CompanyDTO;
import com.foster.pet.entity.Company;
import com.foster.pet.exception.CompanyNotFoundException;
import com.foster.pet.repository.CompanyRepository;
import com.foster.pet.service.CompanyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<CompanyDTO> findAll() {
        log.info("Start - CompanyServiceImpl.findAll");
        List<Company> companies = this.companyRepository.findAll();

        log.debug("End - CompanyServiceImpl.findAll - List<Company>");
        return companies.stream().map(company -> mapper.map(company, CompanyDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Company findById(Long id) {
        log.info("Start - CompanyServiceImpl.findById - Id: {}", id);

        Optional<Company> optCompany = this.companyRepository.findById(id);
        if (optCompany.isEmpty()) {
            log.error("CompanyNotFoundException - Id: {}", id);
            throw new CompanyNotFoundException(ErrorCode.COMPANY_NOT_FOUND.getMessage());
        }

        log.debug("End - CompanyServiceImpl.findById - Company {}", optCompany.get().toString());
        return optCompany.get();
    }

    @Override
    public Company findByCnpj(String cnpj) {
        log.info("Start - CompanyServiceImpl.findByCpf - CNPJ: {}", cnpj);

        Optional<Company> optCompany = this.companyRepository.findByCnpj(cnpj);
        if (optCompany.isEmpty()) {
            log.error("CompanyNotFoundException - CNPJ: {}", cnpj);
            throw new CompanyNotFoundException(ErrorCode.COMPANY_NOT_FOUND.getMessage());
        }

        log.debug("End - CompanyServiceImpl.findByCnpj - Company {}", optCompany.get().toString());
        return optCompany.get();
    }

    @Override
    public Company persist(Company company) {
        log.debug("End - CompanyServiceImpl.persist - Company {}", company.toString());
        return this.companyRepository.save(company);
    }

    @Override
    public CompanyDTO deleteById(Long id) {
        log.info("Start - CompanyServiceImpl.deleteById - Id: {}", id);

        Optional<Company> optCompany = this.companyRepository.findById(id);
        if (optCompany.isEmpty()) {
            log.error("CompanyNotFoundException - Id: {}", id);
            throw new CompanyNotFoundException(ErrorCode.COMPANY_NOT_FOUND.getMessage());
        }

        this.companyRepository.deleteById(id);
        CompanyDTO companyDTO = this.mapper.map(optCompany.get(), CompanyDTO.class);

        log.debug("End - CompanyServiceImpl.deleteById - Company {}", companyDTO.toString());
        return companyDTO;
    }
}
