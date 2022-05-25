package com.project.jobsearch.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CompanyTest {

    @Test
    public void getAndSetTest() {
        Long x = 1L;
        Job job = new Job();
        job.setJobTitle("java developer");
        job.setJobDescription("x no information");
        job.setSkill("java");
        List<Job> xList = new ArrayList<>();
        xList.add(job);
        Company obj = new Company(1, "rahul", "hyd", "good", xList);
        obj.setId(1L);
        obj.setCompanyDetails("x Information");
        obj.setCompanyName("x company");
        obj.setLocation("delhi");
        obj.setJobList(xList);
        assertEquals("x company",obj.getCompanyName());
        assertEquals("delhi",obj.getLocation());
        assertEquals("x Information",obj.getCompanyDetails());
        assertNotNull(obj.getId());
        assertNotNull(obj.getJobList());
    }

}