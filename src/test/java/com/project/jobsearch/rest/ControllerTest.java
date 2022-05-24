package com.project.jobsearch.rest;
import com.project.jobsearch.entity.Company;
import com.project.jobsearch.entity.Job;
import com.project.jobsearch.repository.CompanyRepo;
import com.project.jobsearch.repository.JobRepo;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

@RunWith(SpringJUnit4ClassRunner.class)

public class ControllerTest {
    @Autowired
    private MockMvc mocMvc;
    @MockBean
    private CompanyRepo restController;

    @MockBean
    private JobRepo jobRepo;
    @org.junit.Test
    public void createAndSaveData() throws Exception {
        Company mocCompany=new Company();
        Job mocJob=new Job();
        mocCompany.setId(15L);
        mocCompany.setCompanyName("zemoso");
        mocCompany.setLocation("Hyderabad");
        mocCompany.setCompanyDetails("the zemoso is the one of the best company for learners");
        mocJob.setId(15L);
        mocJob.setJobTitle("full stack");
        mocJob.setJobDescription("the role is deals with the front end and back end");
        mocJob.setSkill("java");
        List<Job> addValue=new ArrayList<>();
        addValue.add(mocJob);
        mocCompany.setJobList(addValue);
        restController.save(mocCompany);
        verify(restController, times(1)).save(mocCompany);
    }
    @Test
    public void findByIdTest() {
        long id=15L;
        Company company=new Company();
        when(restController.findById(id)).thenReturn(Optional.of(company));

        Assertions.assertEquals(Optional.of(company), restController.findById(id));
    }
    @Test
    public void readAllTest()throws Exception{
        List<Company> xList=restController.findAll();
        assertNotNull(xList);
    }
    @Test
    public void readAllJobs() throws Exception{
        List<Job> yList=jobRepo.findAll();
        assertNotNull(yList);
    }
    @Test
    public void deleteByIdTest() {
        long id = 10L;
        restController.deleteById(id);
        verify(restController, times(1)).deleteById(id);

    }
    //..................................................................................................................
    @Test
    public void jobFindByIdTest() {
        long id=15L;
        Job job=new Job();
        when(jobRepo.findById(id)).thenReturn(Optional.of(job));

        Assertions.assertEquals(Optional.of(job), jobRepo.findById(id));
    }

    @Test
    public void jobDeleteByIdTest() {
        long id = 15L;
        jobRepo.deleteById(id);
        verify(jobRepo, times(1)).deleteById(id);}
    @Test
    public void jobCreateTest() {
        Job mocJob=new Job();
        mocJob.setId(16L);
        mocJob.setJobTitle("full stack");
        mocJob.setJobDescription("the role is deals with the front end and back end");
        mocJob.setSkill("java");


        jobRepo.save(mocJob);
        verify(jobRepo, times(1)).save(mocJob);

        }
    @Test
    public void jobSearchTest(){
        String skill="java";
        String location="Hyd";
        List<Company> x=restController.findByKeyword(skill);
        for(Company y:x){
            List<Job>jList=y.getJobList();
            for(Job j:jList){
                assertEquals(skill,j.getSkill());
            }
        }
        List<Company> listOf=restController.findByKeyword(location);
        for(Company com:listOf){
             assertEquals(location,com.getLocation());
        }
    }
    @Test
    public void jobSearchByLocationTest(){
        String location="Hyd";
        List<Company> listOf=restController.findByLocation(location);
        for(Company com:listOf) {
            assertEquals(location, com.getLocation());
        }
    }

}