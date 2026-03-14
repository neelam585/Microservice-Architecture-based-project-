package com.candidate_service.candidate.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateRequest {
    private String name;
    private String primarySkill;
    private String secondarySkill;
    private String qualification;
    private String designation;
    private String location;
    private Double experience;
    private Integer noticePeriod;
}
