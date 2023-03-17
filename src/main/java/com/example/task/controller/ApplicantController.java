package com.example.task.controller;

import com.example.task.controller.util.RestUtil;
import com.example.task.dto.ApplicantDTO;
import com.example.task.exception.NotFoundException;
import com.example.task.service.ApplicantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;


import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/applicant")
@Slf4j
public class ApplicantController {

    private final ApplicantService applicantService;

    @Autowired
    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ApplicantDTO> getAllApplicants() {
        log.info("Calling operation \"getApplicants\"");
        return applicantService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApplicantDTO getApplicantById(@PathVariable Long id) {
        log.info("Calling operation \"getApplicantById\", id={}", id);
        ApplicantDTO dto = applicantService.findById(id);
        if (dto == null) {
            throw new NotFoundException("Applicant with id " + id + " not found!");
        }
        return dto;
    }

    @GetMapping(value = "/detail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApplicantDTO getDetailApplicantById(@PathVariable Long id) {
        log.info("Calling operation \"getDetailApplicantById\", id={}", id);
        ApplicantDTO dto = applicantService.findById(id);
        if (dto == null) {
            throw new NotFoundException("Applicant with id " + id + " not found!");
        }
        return dto;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNewApplicant(@RequestBody ApplicantDTO applicant) {
        log.info("Calling operation \"createApplicant\"");
        applicantService.saveApplicant(applicant);
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/{id}", applicant.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateApplicant(@PathVariable Long id, @RequestBody ApplicantDTO applicant) {
        log.info("Calling operation \"updateApplicant\" with id {}", id);
        final ApplicantDTO original = getApplicantById(id);
        if (!original.getId().equals(applicant.getId())) {
            throw new ValidationException("Applicant id in the data does not match the one in the request URL");
        }
        Long updatedId = applicantService.saveApplicant(applicant);
        log.info("Applicant with id {} was successfully updated", updatedId);
    }
    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeApplicant(@PathVariable Long id) {
        log.info("Calling operation \"deleteApplicant\" with id {}", id);
        applicantService.deleteApplicant(id);
    }

}
