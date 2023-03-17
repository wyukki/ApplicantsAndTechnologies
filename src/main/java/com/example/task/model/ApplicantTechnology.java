package com.example.task.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.validator.constraints.Range;

import java.util.Objects;

@Entity
public class ApplicantTechnology {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    private Applicant applicant;
    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    private Technology technology;
    @Column(name = "level", nullable = false)
    @Range(min = 1, max = 10)
    private Integer level;

    @Column(name = "description")
    private String description;

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicantTechnology that)) return false;
        return id.equals(that.id) && applicant.equals(that.applicant) && technology.equals(that.technology);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, applicant, technology);
    }
}
