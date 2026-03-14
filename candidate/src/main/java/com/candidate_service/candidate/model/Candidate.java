package com.candidate_service.candidate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CondidateId;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    private String primarySkill;
    @NotBlank(message = "Email is required")
    private String secondarySkill;
    @NotBlank(message = "Email is required")
    private String qualification;
    private String designation;
    private String location;

    private Double experience;
    private Integer noticePeriod;
}
