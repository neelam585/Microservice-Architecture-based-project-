package com.interview_schedule_service.interview_schedule.external;

import lombok.Data;

@Data
public class Candidate {

    private Long CondidateId;
    private String name;
    private String primarySkill;
    private String secondarySkill;
    private String qualification;
    private String designation;
    private String location;
    private Double experience;
    private Integer noticePeriod;
}
