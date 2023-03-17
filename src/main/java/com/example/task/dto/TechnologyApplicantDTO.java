package com.example.task.dto;

import lombok.Data;

@Data
public class TechnologyApplicantDTO {
    private Long id;
    private String description;
    private Integer level;
    private Long applicantId;
    private String firstName;
    private String lastName;
    private Integer age;
}
