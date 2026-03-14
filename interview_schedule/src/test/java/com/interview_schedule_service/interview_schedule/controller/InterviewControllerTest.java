package com.interview_schedule_service.interview_schedule.controller;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview_schedule_service.interview_schedule.payload.InterviewScheduleRequest;
import com.interview_schedule_service.interview_schedule.payload.InterviewScheduleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.interview_schedule_service.interview_schedule.model.Interview;
import com.interview_schedule_service.interview_schedule.service.InterviewService;
import org.springframework.http.ResponseEntity;
import java.util.List;

class InterviewControllerTest {

    @Mock
    private InterviewService interviewService;

    @InjectMocks
    private InterviewController interviewController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testScheduleInterview() {
        InterviewScheduleRequest request = new InterviewScheduleRequest();
        request.setCandidateId(1L);
        request.setFinalStatus(true);

        Interview interview = new Interview();
        interview.setCandidateId(1L);
        interview.setFinalStatus(true);

        when(interviewService.scheduleInterview(request)).thenReturn(interview);

        ResponseEntity<Interview> response = interviewController.scheduleInterview(request);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(true, response.getBody().getFinalStatus());
    }

    @Test
    void testCancelInterview() {
        when(interviewService.cancelInterview(1L)).thenReturn("deleted");

        ResponseEntity<String> response = interviewController.cancelInterview(1L);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("deleted", response.getBody());
    }

    @Test
    void testUpdateInterview() {
        Interview request = new Interview();
        request.setCandidateId(2L);
        request.setFinalStatus(false);

        Interview updated = new Interview();
        updated.setCandidateId(2L);
        updated.setFinalStatus(false);

        when(interviewService.updateInterview(1L, request)).thenReturn(updated);

        ResponseEntity<Interview> response = interviewController.updateInterview(1L, request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(false, response.getBody().getFinalStatus());
    }

    @Test
    void testGetAllInterview() {
        InterviewScheduleResponse response = new InterviewScheduleResponse();
        response.setCandidateId(1L);
        response.setFinalStatus(true);


        when(interviewService.getAllInterview()).thenReturn(List.of(response));

        ResponseEntity<List<InterviewScheduleResponse>> result = interviewController.getAllInterview();

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1, result.getBody().size());

    }
}
