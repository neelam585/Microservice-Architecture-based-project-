package com.candidate_service.candidate.controller;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import com.candidate_service.candidate.model.Candidate;
import com.candidate_service.candidate.payload.CandidateRequest;
import com.candidate_service.candidate.payload.CandidateResponse;
import com.candidate_service.candidate.service.CandidateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class CandidateControllerTest {


    @Mock
    private CandidateService candidateService;

    @InjectMocks
    private CandidateController candidateController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCandidate() {
        CandidateRequest request = new CandidateRequest();
        request.setName("Neelam");

        Candidate candidate = new Candidate();
        candidate.setName("Neelam");

        when(candidateService.createCandidate(request)).thenReturn(candidate);

        ResponseEntity<Candidate> response = candidateController.createCandidate(request);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Neelam", response.getBody().getName());

    }

    @Test
    public void testGetAllCandidate() {
        CandidateResponse res1 = new CandidateResponse();
        res1.setName("Alice");
        CandidateResponse res2 = new CandidateResponse();
        res2.setName("Bob");

        when(candidateService.getAllCandidate()).thenReturn(Arrays.asList(res1, res2));

        ResponseEntity<List<CandidateResponse>> response = candidateController.getAllCandidate();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals("Alice", response.getBody().get(0).getName());
    }

    @Test
    public void testGetCandidateById_Found() {
        CandidateResponse response = new CandidateResponse();
        response.setName("Neelam");

        when(candidateService.getCandidateById(1L)).thenReturn(response);

        ResponseEntity<CandidateResponse> result = candidateController.getCandidateById(1L);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Neelam", result.getBody().getName());
    }

    @Test
    public void testGetCandidateById_NotFound() {
        when(candidateService.getCandidateById(2L)).thenReturn(null);

        ResponseEntity<CandidateResponse> result = candidateController.getCandidateById(2L);

        assertEquals(404, result.getStatusCodeValue());
        assertNull(result.getBody());

    }
}