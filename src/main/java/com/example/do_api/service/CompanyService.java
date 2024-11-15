package com.example.do_api.service;

import com.example.do_api.model.Company;
import com.example.do_api.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    // Create or Update a company
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    // Get a company by ID
    public Optional<Company> getCompany(Long id) {
        return companyRepository.findById(id);
    }

    // Get all companies
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Delete a company by ID
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
