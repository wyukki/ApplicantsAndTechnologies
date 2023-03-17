package com.example.task.service;

import com.example.task.dto.TechnologyDTO;
import com.example.task.mapper.TechnologyMapper;
import com.example.task.model.Technology;
import com.example.task.repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TechnologyService {
    private final TechnologyRepository technologyRepository;

    @Autowired
    public TechnologyService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    public TechnologyDTO findById(Long id) {
        Optional<Technology> entity = technologyRepository.findById(id);
        return entity.map(TechnologyMapper::entityToDTO).orElse(null);
    }

    public List<TechnologyDTO> findAll() {
        List<Technology> technologies = technologyRepository.findAll();
        return technologies.stream().map(TechnologyMapper::entityToDTO).toList();
    }

    public Long save(TechnologyDTO dto) {
        return technologyRepository.save(TechnologyMapper.dtoToEntity(dto)).getId();
    }

    public void delete(Long id) {
        technologyRepository.deleteById(id);
    }
}
