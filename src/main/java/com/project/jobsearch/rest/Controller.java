package com.project.jobsearch.rest;

import com.project.jobsearch.dto.CompanyConverter;
import com.project.jobsearch.dto.CompanyDto;
import com.project.jobsearch.repository.CompanyRepo;
import com.project.jobsearch.repository.JobRepo;
import com.project.jobsearch.entity.Company;
import com.project.jobsearch.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private CompanyRepo companyRepository;

    @Autowired
    private JobRepo jobRepository;

    @Autowired
    private CompanyConverter converter;

    //searching the location or skill..................................................................

    @GetMapping("/search/{value}")
    public List<CompanyDto> findByKeyword(@PathVariable String value) {
        List<Company> xList=companyRepository.findByKeyword(value);
        return converter.entityToDto(xList);
    }

    //searching by location..........................................................................
    @GetMapping("/search/location/{value}")
    public List<CompanyDto> findByLocation(@PathVariable String value) {
       List<Company> xList=companyRepository.findByLocation(value);
       return converter.entityToDto(xList);
    }

    //Listing the all the company's....................................................................
    @GetMapping("/company")
    public List<CompanyDto> getCompany() {
        List<Company> xList=companyRepository.findAll();
        return converter.entityToDto(xList);
    }
    //add the company................................................................................
    @PostMapping("/company")
    public CompanyDto saveData(@RequestBody CompanyDto company) {
        Company req=converter.dtoToEntity(company);
        req=companyRepository.save(req);
        CompanyDto companyDto = converter.entityToDto(req);
        return companyDto;
    }

    //delete the company.................................................................................
    @DeleteMapping("/company/{id}")
    public String getDeleteById(@PathVariable("id") long id) {
        try {
            companyRepository.deleteById(id);
            return "the record is deleted";

        }catch (Exception e){
            return "the record  is not there ";
        }


    }
    //update the company details...............................................................................

    @PutMapping("/company/{id}")
    public String updateCompany(@PathVariable("id") long id, @RequestBody CompanyDto company) {

        //check if employee exist in database
     try{Company obj = companyRepository.getById(id);


            obj.setCompanyName(company.getCompanyName());
            obj.setCompanyDetails(company.getCompanyDescription());
            obj.setLocation(company.getLocation());
            obj.setJobList(company.getJobList());
            companyRepository.save(obj);
            return "updated";

     }catch (Exception e){
        return "the record is not there ";
    }



    }
// deleting all the company's........................................................................................
    @DeleteMapping("/company")
    public String deleteAllEmployees() {
        try {
            companyRepository.deleteAll();
            return "deleted all";
        } catch (Exception e){
        return "the record is not there ";
    }

    }
    // the job is removed from the company.........................................................................
    @DeleteMapping("/job/{id}/{jobid}")
     public String deleteTheJobFromCompanyByCompanyId(@PathVariable("id") Long id,@PathVariable Long jobid){
        try{
            Company obj = companyRepository.getById(id);
            for(Job s:obj.getJobList()){
                if(s.getId()==jobid) {
                    obj.getJobList().remove(s);
                    companyRepository.save(obj);
                    return "the element is removed";
                }

            }
            return "the element is not present";

        }catch (Exception e){
            return "the element is not present";
        }

     }
     // listing of jobs...........................................................................................
    @GetMapping("/job")
    public List<Job> getJob(){
        return jobRepository.findAll();
    }

    //adding the job to the company
    @PutMapping("/job/addJob/{id}")
    public Company addJob(@PathVariable("id") Long id,@RequestBody Job job){

            Company obj = companyRepository.getById(id);
                obj.getJobList().add(job);
                return companyRepository.save(obj);
            }

    @PostMapping("/job")
    public Job addJobToDB(@RequestBody Job job){
        return jobRepository.save(job);
    }
    @DeleteMapping("/job/{id}")
    public String deleteById(@PathVariable("id") Long id){
        try{
            jobRepository.deleteById(id);
            return "the job was deleted";
        }
        catch (Exception e){
            return "the id is not present";
        }

    }
    @PutMapping("/job/{id}")
    public String updateById(@PathVariable("id") Long id,@RequestBody Job job){
        try{
            Job obj=jobRepository.getById(id);
            obj.setJobTitle(job.getJobTitle());
            obj.setJobDescription(job.getJobDescription());
            obj.setSkill(job.getSkill());
            jobRepository.save(obj);
            return "the id="+id+" is updated";
        }catch (Exception e){
            return "the id is not there";
        }
    }
    @GetMapping("/job/{idd}")
    public Optional<Job> getJobById(@PathVariable("idd") Long idd){
        return jobRepository.findById(idd);
    }
    @GetMapping("/company/{id}")
    public Optional<Company> getCompanyById(@PathVariable("id") Long id){
        return companyRepository.findById(id);
    }
}


