package com.example.task.service;

import com.example.task.dto.ApplicantDTO;
import com.example.task.mapper.ApplicantMapper;
import com.example.task.model.Applicant;
import com.example.task.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApplicantService {
    private final ApplicantRepository applicantRepository;

    @Autowired
    public ApplicantService(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    public ApplicantDTO findById(Long id) {
        Optional<Applicant> entity = applicantRepository.findById(id);
        return entity.map(ApplicantMapper::entityToDto).orElse(null);
    }

    public Long saveApplicant(ApplicantDTO applicantDTO) {
        Applicant applicant = applicantRepository.save(ApplicantMapper.dtoToEntity(applicantDTO));
        return applicant.getId();
    }

    public List<ApplicantDTO> findAll() {
        List<Applicant> applicants = applicantRepository.findAll();
        List<ApplicantDTO> dtos = applicants.stream()
                .map(ApplicantMapper::entityToDto).toList();
        return dtos;
    }

    public void deleteApplicant(Long id) {
        applicantRepository.deleteById(id);
    }
}
