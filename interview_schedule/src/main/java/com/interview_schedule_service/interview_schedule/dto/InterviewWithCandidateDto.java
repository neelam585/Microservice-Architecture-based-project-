package com.interview_schedule_service.interview_schedule.dto;

import com.interview_schedule_service.interview_schedule.external.Candidate;
import com.interview_schedule_service.interview_schedule.model.Interview;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewWithCandidateDto {
    private Interview interview;
    private Candidate candidate;
}
