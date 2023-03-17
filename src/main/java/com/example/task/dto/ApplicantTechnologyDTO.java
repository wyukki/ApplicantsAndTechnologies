package com.example.task.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicantTechnologyDTO {
    private Long id;
    private Long technologyId;
    private String technologyName;
    private Integer level;
    private String description;
}
