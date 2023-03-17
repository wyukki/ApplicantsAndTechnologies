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
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "age")
    private Integer age;

    @OneToMany(mappedBy = "applicant")
    private List<ApplicantTechnology> technologies = new ArrayList<>();

    public void addApplicant(ApplicantTechnology applicant) {
        this.technologies.add(applicant);
    }

    public void removeApplicant(ApplicantTechnology applicant) {
        this.technologies.removeIf(applicantTechnology ->
                applicantTechnology.equals(applicant));
    }

    public List<ApplicantTechnology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<ApplicantTechnology> technologies) {
        this.technologies = technologies;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Applicant applicant)) return false;
        return id.equals(applicant.id) && firstName.equals(applicant.firstName) && lastName.equals(applicant.lastName) && Objects.equals(age, applicant.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age);
    }
}
