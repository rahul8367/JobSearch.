package com.project.jobsearch.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Job")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "job_description")
    private String jobDescription;
    @Column(name="skill")
    private String skill;
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "jobList")
    @JsonIgnore
    private List<Company> listOfCompany =new ArrayList<>();

    public void setListOfCompany(Company listOfCompany) {
        this.listOfCompany.add(listOfCompany);
    }
}
