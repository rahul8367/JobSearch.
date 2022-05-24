package com.project.jobsearch.dto;

import com.project.jobsearch.entity.Job;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private Long id;
    private String companyName;
    private String location;
    private String companyDescription;
    private List<Job> jobList=new ArrayList<>();
}
