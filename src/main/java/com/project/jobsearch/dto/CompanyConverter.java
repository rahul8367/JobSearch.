package com.project.jobsearch.dto;

import com.project.jobsearch.entity.Company;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyConverter {
    public CompanyDto entityToDto(Company company){
        CompanyDto con=new CompanyDto();
        con.setCompanyName(company.getCompanyName());
        con.setId(company.getId());
        con.setLocation(company.getLocation());
        con.setCompanyDescription(company.getCompanyDetails());
        con.setJobList(company.getJobList());
        return con;
    }
    public Company dtoToEntity(CompanyDto company){
        Company con=new Company();
        con.setCompanyName(company.getCompanyName());
        con.setId(company.getId());
        con.setLocation(company.getLocation());
        con.setCompanyDetails(company.getCompanyDescription());
        con.setJobList(company.getJobList());
        return con;
    }
    public List<CompanyDto> entityToDto(List<Company> company) {

        return	company.stream().map(x -> entityToDto(x)).collect(Collectors.toList());

    }
    public List<Company> dtoToEntity(List<CompanyDto> dto)
    {

        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }

}
