package com.project.jobsearch.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JobTest {


    @Test
    public void getAndSetTest() {
        Long x=1L;
        Job job = new Job();
        job.setJobTitle("java developer");
        job.setJobDescription("x no information");
        job.setSkill("java");
        List<Job> xList = new ArrayList<>();
        xList.add(job);
        Company obj = new Company(1, "rahul", "hyd", "good",xList );
        job.setListOfCompany(obj);
        assertEquals("java developer",job.getJobTitle());
        assertEquals("x no information",job.getJobDescription());
        assertEquals("java",job.getSkill());
        assertEquals(obj,job.getListOfCompany().get(0));
        assertNotNull(job.getId());


    }

}