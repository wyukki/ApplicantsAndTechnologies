package com.example.task.mapper;

import com.example.task.dto.ApplicantDTO;
import com.example.task.dto.ApplicantTechnologyDTO;
import com.example.task.model.Applicant;
import com.example.task.model.ApplicantTechnology;
import com.example.task.model.Technology;

import java.util.List;

public class ApplicantMapper {
    public static ApplicantDTO entityToDto(Applicant applicant) {
        ApplicantDTO dto = new ApplicantDTO();
        dto.setId(applicant.getId());
        dto.setFirstName(applicant.getFirstName());
        dto.setLastName(applicant.getLastName());
        dto.setTechnologies(listEntityToDTO(applicant.getTechnologies()));
        return dto;
    }

    private static List<ApplicantTechnologyDTO> listEntityToDTO(List<ApplicantTechnology> entity) {
        return entity.stream().map(ApplicantMapper::entityToDTO).toList();
    }

    private static ApplicantTechnologyDTO entityToDTO(ApplicantTechnology entity) {
        ApplicantTechnologyDTO dto = new ApplicantTechnologyDTO();
        dto.setId(entity.getId());
        dto.setLevel(entity.getLevel());
        dto.setDescription(entity.getDescription());
        dto.setTechnologyId(entity.getTechnology().getId());
        dto.setTechnologyName(entity.getTechnology().getName());
        return dto;
    }

    public static Applicant dtoToEntity(ApplicantDTO dto) {
        Applicant applicant = new Applicant();
        applicant.setId(dto.getId());
        applicant.setFirstName(dto.getFirstName());
        applicant.setLastName(dto.getLastName());
        applicant.setAge(dto.getAge());
        applicant.setTechnologies(ApplicantMapper.listDTOsToEntity(dto.getTechnologies()));
        return applicant;
    }

    private static List<ApplicantTechnology> listDTOsToEntity(List<ApplicantTechnologyDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) return null;
        return dtos.stream().map(ApplicantMapper::dtoToEntity).toList();
    }
    private static ApplicantTechnology dtoToEntity(ApplicantTechnologyDTO dto) {
        ApplicantTechnology applicantTechnology = new ApplicantTechnology();
        applicantTechnology.setTechnology(ApplicantMapper.dtoToTechnologyEntity(dto));
        applicantTechnology.setDescription(dto.getDescription());
        applicantTechnology.setLevel(dto.getLevel());
        applicantTechnology.setId(dto.getId());
        return applicantTechnology;
    }

    private static Technology dtoToTechnologyEntity(ApplicantTechnologyDTO dto) {
        Technology technology = new Technology();
        technology.setId(dto.getTechnologyId());
        technology.setName(dto.getTechnologyName());
        return technology;
    }
}
