package com.interview_schedule_service.interview_schedule.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import com.interview_schedule_service.interview_schedule.exception.ResourceNotFoundException;
import com.interview_schedule_service.interview_schedule.model.Interview;
import com.interview_schedule_service.interview_schedule.payload.InterviewScheduleRequest;
import com.interview_schedule_service.interview_schedule.repository.InterviewScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InterviewServiceImplTest {

        @Mock
        private InterviewScheduleRepository interviewScheduleRepository;

        @InjectMocks
        private InterviewServiceImpl interviewService;

        @Test
        void testScheduleInterview() {
            InterviewScheduleRequest request = new InterviewScheduleRequest();
            request.setCandidateId(1L);
            request.setTechRating(String.valueOf(4));
            request.setHrRating(String.valueOf(3));
            request.setFinalStatus(true);

            Interview savedInterview = new Interview();
            savedInterview.setCandidateId(1L);
            savedInterview.setTechRating(String.valueOf(4));
            savedInterview.setHrRating(String.valueOf(3));
            savedInterview.setFinalStatus(true);

            when(interviewScheduleRepository.save(any(Interview.class))).thenReturn(savedInterview);


                    Interview result = interviewService.scheduleInterview(request);

            assertNotNull(result);
            assertEquals(true, result.getFinalStatus());
            verify(interviewScheduleRepository, times(1)).save(any(Interview.class));
        }

        @Test
        void testCancelInterview_Success() {
            Long id = 1L;
            Interview interview = new Interview();
            interview.setInterviewId(id);

            when(interviewScheduleRepository.findById(id)).thenReturn(Optional.of(interview));
            doNothing().when(interviewScheduleRepository).deleteById(id);

            String result = interviewService.cancelInterview(id);

            assertEquals("data has been deleted", result);
            verify(interviewScheduleRepository).deleteById(id);
        }

    @Test
    void testGetInterviewById_NotFound() {
        Long id = 1L;
        when(interviewScheduleRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> interviewService.getInterviewById(id));
    }

    @Test
    void testUpdateInterview() {
        Long id = 1L;
        Interview existing = new Interview();
        existing.setInterviewId(id);
        existing.setCandidateId(1L);

        Interview updated = new Interview();
        updated.setCandidateId(2L);
        updated.setHrRating(String.valueOf(4));
        updated.setFinalStatus(false);

        when(interviewScheduleRepository.findById(id)).thenReturn(Optional.of(existing));
        when(interviewScheduleRepository.save(any(Interview.class))).thenReturn(updated);

        Interview result = interviewService.updateInterview(id, updated);

        assertEquals(2L, result.getCandidateId());
        assertEquals(false, result.getFinalStatus());
    }

}

