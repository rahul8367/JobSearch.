package com.project.jobsearch.controller;
import com.project.jobsearch.custom.exception.NotFoundException;
import com.project.jobsearch.custom.exception.NullException;
import com.project.jobsearch.dto.CompanyConverter;
import com.project.jobsearch.dto.CompanyDto;
import com.project.jobsearch.repository.CompanyRepo;
import com.project.jobsearch.repository.JobRepo;
import com.project.jobsearch.entity.Company;
import com.project.jobsearch.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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

    //listing the company by id.....................................................................
    @GetMapping("/company/{id}")
    public Company getCompanyById(@PathVariable("id") Long id){
        Company com=companyRepository.findById(id).orElseThrow(() -> new NotFoundException("company not found with id :" + id));
        return com;
    }

    //add the company................................................................................
    @PostMapping("/company")
    public  CompanyDto saveData(@RequestBody CompanyDto company) {
        Company req=converter.dtoToEntity(company);
        if(req.getLocation().length()<=2){
            throw new NullException("the location is must not null and it must contains more then 2 characters");
        }
        else if(req.getCompanyName().length()<=2){
            throw new NullException("the company name field is must not null ");}
        else if(req.getJobList().size()>=1){
            for(Job j: req.getJobList()){
                if(j.getSkill().length()<=1){
                    throw new NullException("the skill field is must not null ");
                }
                else if(j.getJobTitle().length()<=1){
                    throw new NullException("the jobTitle field is must not null ");
                }
            }
        }
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
            throw new NotFoundException("company not found with id ="+id);
        }


    }
    //update the company details...............................................................................

    @PutMapping("/company/{id}")
    public String updateCompany(@PathVariable("id") long id, @RequestBody CompanyDto company) {
        //check if employee exist in database
         Company obj=companyRepository.findById(id).orElseThrow(() -> new NotFoundException("company not found with id :" + id));
         obj.setCompanyName(company.getCompanyName());
         obj.setCompanyDetails(company.getCompanyDescription());
         obj.setLocation(company.getLocation());
         obj.setJobList(company.getJobList());
         if(obj.getLocation().length()<=2){
             throw new NullException("the location is must not null and it must contains more then 2 characters");
         }
         else if(obj.getCompanyName().length()<=2){
             throw new NullException("the company name field is must not null ");}
         else if(obj.getJobList().size()>=1){
             for(Job j: obj.getJobList()){
                 if(j.getSkill().length()<=1){
                     throw new NullException("the skill field is must not null ");
                 }
                 else if(j.getJobTitle().length()<=1){
                     throw new NullException("the jobTitle field is must not null ");
                 }
             }
         }
            companyRepository.save(obj);
            return "the record is updated";
    }
    // deleting all the company's........................................................................................
    @DeleteMapping("/company")
    public String deleteAllEmployees() {
            companyRepository.deleteAll();
            return "deleted all";
    }

    // the job is removed from the company.........................................................................
    @DeleteMapping("/job/{id}/{jobid}")
     public String deleteTheJobFromCompanyByCompanyId(@PathVariable("id") Long id,@PathVariable Long jobid){
            Company obj=companyRepository.findById(id).orElseThrow(() -> new NotFoundException("company not found with id :" + id));
            for(Job s:obj.getJobList()){
                if(s.getId()==jobid) {
                    obj.getJobList().remove(s);
                    companyRepository.save(obj);
                    return "the element is removed";
                }
            }
            return "the element is not present: "+jobid;
     }

     // listing of jobs...........................................................................................
    @GetMapping("/job")
    public List<Job> getJob(){
        return jobRepository.findAll();
    }


    //adding the job to the company.....................................................................................
    @PutMapping("/company/{id}/job")
    public Company addJob(@PathVariable("id") Long id,@RequestBody Job job){

        Company obj=companyRepository.findById(id).orElseThrow(() -> new NotFoundException("company not found with id :" + id));
        if(job.getSkill().length()<=1){
            throw new NullException("the skill field is must not null ");
        }
        else if(job.getJobTitle().length()<=1){
            throw new NullException("the jobTitle field is must not null ");
        }
        obj.getJobList().add(job);
        return companyRepository.save(obj);
    }

    //add job in the data base............................................................................................
    @PostMapping("/job")
    public Job addJobToDB(@RequestBody Job job){
        if(job.getSkill().length()<=1){
            throw new NullException("the skill field is must not null ");
        }
        else if(job.getJobTitle().length()<=1){
            throw new NullException("the jobTitle field is must not null ");
        }
        return jobRepository.save(job);
    }

//deleting the job throw the id........................................................................................
    @DeleteMapping("/job/{id}")
    public String deleteById(@PathVariable("id") Long id){
        try{
            jobRepository.deleteById(id);
            return "the job was deleted";
        }
        catch (Exception e){
            throw new NotFoundException("job not found with id ="+id);
        }

    }
    //updating the job throw id.........................................................................................
    @PutMapping("/job/{id}")
    public String updateById(@PathVariable("id") Long id,@RequestBody Job job){
        Job obj=jobRepository.findById(id).orElseThrow(() -> new NotFoundException("job not found with id :" + id));
        if(job.getSkill().length()<=1){
            throw new NullException("the skill field is must not null ");
        }
        else if(job.getJobTitle().length()<=1){
            throw new NullException("the jobTitle field is must not null ");
        }
            obj.setJobTitle(job.getJobTitle());
            obj.setJobDescription(job.getJobDescription());
            obj.setSkill(job.getSkill());
            jobRepository.save(obj);
            return "the id="+id+" is updated";
        }

//find the job throw job id.............................................................................................
    @GetMapping("/job/{id}")
    public Job getJobById(@PathVariable("id") Long id){
        Job obj=jobRepository.findById(id).orElseThrow(() -> new NotFoundException("job not found with id :" + id));
        return obj;
    }
}


