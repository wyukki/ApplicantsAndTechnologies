package com.example.task.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "technology")
    private List<ApplicantTechnology> applicants = new ArrayList<>();

    public void addApplicant(ApplicantTechnology applicant) {
        this.applicants.add(applicant);
    }

    public void removeApplicant(ApplicantTechnology applicant) {
        this.applicants.removeIf(applicantTechnology ->
                applicantTechnology.equals(applicant));
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Technology that)) return false;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public List<ApplicantTechnology> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<ApplicantTechnology> applicantTechnologies) {
        this.applicants = applicantTechnologies;
    }
}
