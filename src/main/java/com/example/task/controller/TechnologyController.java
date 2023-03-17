package com.example.task.controller;

import com.example.task.controller.util.RestUtil;
import com.example.task.dto.TechnologyDTO;
import com.example.task.exception.NotFoundException;
import com.example.task.service.TechnologyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/technology")
@Slf4j
public class TechnologyController {
    private final TechnologyService technologyService;
    @Autowired
    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TechnologyDTO> getAllTechnologies() {
        log.info("Calling operation \"getAllTechnologies\"");
        return technologyService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TechnologyDTO getTechnologyById(@PathVariable Long id) {
        log.info("Calling operation \"getTechnologyById\", id={}", id);
        TechnologyDTO dto = technologyService.findById(id);
        if (dto == null) {
            throw new NotFoundException("Applicant with id " + id + " not found!");
        }
        return dto;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNewTechnology(@RequestBody TechnologyDTO technology) {
        log.info("Calling operation \"createNewTechnology\"");
        technologyService.save(technology);
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/{id}", technology.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTechnology(@PathVariable Long id, @RequestBody TechnologyDTO technology) {
        log.info("Calling operation \"updateTechnology\" with id {}", id);
        final TechnologyDTO original = getTechnologyById(id);
        if (!original.getId().equals(technology.getId())) {
            throw new ValidationException("Technology ID in the data does not match the one in the requested URI");
        }
        Long updatedId = technologyService.save(technology);
        log.info("Technology with ID {} was successfully updated", updatedId);
    }
    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTechnology(@PathVariable Long id) {
        log.info("Calling operation \"removeTechnology\" with id {}", id);
        technologyService.delete(id);
    }
}
