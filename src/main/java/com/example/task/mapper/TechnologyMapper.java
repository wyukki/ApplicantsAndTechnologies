package com.example.task.mapper;

import com.example.task.dto.TechnologyApplicantDTO;
import com.example.task.dto.TechnologyDTO;
import com.example.task.model.Applicant;
import com.example.task.model.ApplicantTechnology;
import com.example.task.model.Technology;

import java.util.List;

public class TechnologyMapper {
    public static Technology dtoToEntity(TechnologyDTO dto) {
        Technology technology = new Technology();
        technology.setId(dto.getId());
        technology.setName(dto.getName());
        technology.setApplicants(listDTOsToEntity(dto.getApplicants()));
        return technology;
    }

    private static List<ApplicantTechnology> listDTOsToEntity(List<TechnologyApplicantDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) return null;
        return dtos.stream().map(TechnologyMapper::dtoToEntity).toList();
    }

    private static ApplicantTechnology dtoToEntity(TechnologyApplicantDTO dto) {
        ApplicantTechnology applicantTechnology = new ApplicantTechnology();
        applicantTechnology.setId(dto.getId());
        applicantTechnology.setDescription(dto.getDescription());
        applicantTechnology.setLevel(dto.getLevel());
        return applicantTechnology;
    }

    private static void appendApplicantAttributes(TechnologyApplicantDTO dto, ApplicantTechnology entity) {
        Applicant applicant = new Applicant();
        applicant.setId(dto.getApplicantId());
        applicant.setAge(dto.getAge());
        applicant.setFirstName(dto.getFirstName());
        applicant.setLastName(dto.getLastName());
        entity.setApplicant(applicant);
    }

    public static TechnologyDTO entityToDTO(Technology entity) {
        TechnologyDTO technologyDTO = new TechnologyDTO();
        technologyDTO.setId(entity.getId());
        technologyDTO.setName(entity.getName());
        technologyDTO.setApplicants(TechnologyMapper.listEntityToDTO(entity.getApplicants()));
        return technologyDTO;
    }

    private static List<TechnologyApplicantDTO> listEntityToDTO(List<ApplicantTechnology> entity) {
        return entity.stream().map(TechnologyMapper::entityToDTO).toList();
    }

    private static TechnologyApplicantDTO entityToDTO(ApplicantTechnology entity) {
        TechnologyApplicantDTO dto = new TechnologyApplicantDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setLevel(entity.getLevel());
        appendApplicantAttributes(entity, dto);
        return dto;
    }

    private static void appendApplicantAttributes(ApplicantTechnology entity, TechnologyApplicantDTO dto) {
        dto.setApplicantId(entity.getApplicant().getId());
        dto.setAge(entity.getApplicant().getAge());
        dto.setFirstName(entity.getApplicant().getFirstName());
        dto.setLastName(entity.getApplicant().getLastName());
    }
}
