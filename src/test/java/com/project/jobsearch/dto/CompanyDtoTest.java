package com.project.jobsearch.dto;

import com.project.jobsearch.entity.Job;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CompanyDtoTest {
//    private long id;
//    private long companyName;
//    private companyDetails ;
//    private location;
    @Test
    public void getIdTest() {
        Long x=1L;
        CompanyDto obj =new CompanyDto();
        obj.setId(x);
        assertEquals(obj.getId(),x);
    }

    @Test
    public void getCompanyNameTest() {
        CompanyDto obj =new CompanyDto();
        obj.setCompanyName("jp tech");
        assertEquals("jp tech",obj.getCompanyName());
    }

    @Test
    public void getLocationTest() {
        CompanyDto obj =new CompanyDto();
        obj.setLocation("hyd");
        assertEquals("hyd",obj.getLocation());
    }


    @Test
    public void getCompanyDescriptionTest() {
        CompanyDto obj =new CompanyDto();
        obj.setCompanyDescription("some thing");
        assertEquals("some thing",obj.getCompanyDescription());
    }
    @Test
    public void allArgumentConstructorTest(){
        Job job=new Job();
        job.setJobTitle("java developer");
        job.setJobDescription("x no information");
        job.setSkill("java");
        List<Job> xList=new ArrayList<>();
        xList.add(job);
        CompanyDto obj =new CompanyDto(1L,"jp tech","hyd","some thing",xList);
        assertEquals("jp tech",obj.getCompanyName());
        assertEquals("some thing",obj.getCompanyDescription());
        assertEquals("hyd",obj.getLocation());
        assertNotNull(obj.getJobList());
    }




}