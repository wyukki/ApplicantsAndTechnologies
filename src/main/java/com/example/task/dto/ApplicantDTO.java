package com.example.task.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ApplicantDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private List<ApplicantTechnologyDTO> technologies;
    private Integer age;
}
