package com.interview_schedule_service.interview_schedule.service;

import com.interview_schedule_service.interview_schedule.dto.InterviewWithCandidateDto;
import com.interview_schedule_service.interview_schedule.model.Interview;
import com.interview_schedule_service.interview_schedule.payload.InterviewScheduleRequest;
import com.interview_schedule_service.interview_schedule.payload.InterviewScheduleResponse;

import java.util.List;

public interface InterviewService {
    Interview scheduleInterview(InterviewScheduleRequest interviewScheduleRequest);
    Interview updateInterview(Long id, Interview updatedInterview);
    String cancelInterview(Long interviewScheduleId);
    Interview getInterviewById(Long id);
    //List<InterviewWithCandidateDto> getAllInterview();
    List<InterviewScheduleResponse> getAllInterview();
}
