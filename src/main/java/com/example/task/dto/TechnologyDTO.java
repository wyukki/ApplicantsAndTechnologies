package com.example.task.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TechnologyDTO {
    private Long id;
    private String name;
    private List<TechnologyApplicantDTO> applicants;
}
