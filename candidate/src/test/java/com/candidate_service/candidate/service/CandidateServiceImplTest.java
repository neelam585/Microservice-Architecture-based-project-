package com.candidate_service.candidate.service;

import com.candidate_service.candidate.exception.ResourceNotFoundException;
import com.candidate_service.candidate.model.Candidate;
import com.candidate_service.candidate.payload.CandidateRequest;
import com.candidate_service.candidate.payload.CandidateResponse;
import com.candidate_service.candidate.repository.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SpringBootTest
public class CandidateServiceImplTest {

    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private CandidateServiceImpl candidateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCandidate() {
        // Arrange
        CandidateRequest request = new CandidateRequest();
        request.setName("John Doe");
        request.setDesignation("Software Engineer");
        request.setLocation("Noida");
        request.setExperience(Double.valueOf("5"));
        request.setQualification("B.Tech");
        request.setNoticePeriod(30);
        request.setPrimarySkill("Java");
        request.setSecondarySkill("Spring Boot");

        Candidate savedCandidate = new Candidate();
        savedCandidate.setName(request.getName());
        savedCandidate.setDesignation(request.getDesignation());
        savedCandidate.setLocation(request.getLocation());
        savedCandidate.setExperience(request.getExperience());
        savedCandidate.setQualification(request.getQualification());
        savedCandidate.setNoticePeriod(request.getNoticePeriod());
        savedCandidate.setPrimarySkill(request.getPrimarySkill());
        savedCandidate.setSecondarySkill(request.getSecondarySkill());
        when(candidateRepository.save(any(Candidate.class))).thenReturn(savedCandidate);
        // Act
        Candidate result = candidateService.createCandidate(request);
        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("Software Engineer", result.getDesignation());
        assertEquals("Noida", result.getLocation());
        assertEquals(5, result.getExperience());
        assertEquals("B.Tech", result.getQualification());
        assertEquals(30, result.getNoticePeriod());
        assertEquals("Java", result.getPrimarySkill());
        assertEquals("Spring Boot", result.getSecondarySkill());

        verify(candidateRepository, times(1)).save(any(Candidate.class));

    }

    @Test
    public void testGetAllCandidate() {
        // Arrange
        Candidate candidate1 = new Candidate();
        candidate1.setName("Alice");
        candidate1.setDesignation("Developer");
        candidate1.setExperience(3.0);
        candidate1.setLocation("Noida");
        candidate1.setNoticePeriod(30);
        candidate1.setPrimarySkill("Java");
        candidate1.setSecondarySkill("Spring");

        Candidate candidate2 = new Candidate();
        candidate2.setName("Bob");
        candidate2.setDesignation("Tester");
        candidate2.setExperience(2.0);
        candidate2.setLocation("Delhi");
        candidate2.setNoticePeriod(15);
        candidate2.setPrimarySkill("Selenium");
        candidate2.setSecondarySkill("Java");

        List<Candidate> candidateList = Arrays.asList(candidate1, candidate2);

        when(candidateRepository.findAll()).thenReturn(candidateList);

        // Act
        List<CandidateResponse> responseList = candidateService.getAllCandidate();

        // Assert
        assertNotNull(responseList);
        assertEquals(2, responseList.size());

        CandidateResponse response1 = responseList.get(0);
        assertEquals("Alice", response1.getName());
        assertEquals("Developer", response1.getDesignation());
        assertEquals(3, response1.getExperience());
        assertEquals("Noida", response1.getLocation());
        assertEquals(30, response1.getNoticePeriod());
        assertEquals("Java", response1.getPrimarySkill());
        assertEquals("Spring", response1.getSecondarySkill());

        CandidateResponse response2 = responseList.get(1);
        assertEquals("Bob", response2.getName());
        assertEquals("Tester", response2.getDesignation());
        assertEquals(2, response2.getExperience());
        assertEquals("Delhi", response2.getLocation());
        assertEquals(15, response2.getNoticePeriod());
        assertEquals("Selenium", response2.getPrimarySkill());

        assertEquals("Java", response2.getSecondarySkill());

        verify(candidateRepository, times(1)).findAll();
    }

    @Test
    public void testGetCandidateById_Success() {
        // Arrange
        Long candidateId = 1L;
        Candidate candidate= new Candidate();
        candidate.setName("Neelam");
        candidate.setDesignation("Senior Engineer");
        candidate.setLocation("Noida");
        candidate.setNoticePeriod(60);
        candidate.setPrimarySkill("Java");
        candidate.setSecondarySkill("Spring Boot");

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));


// Act
        CandidateResponse response = candidateService.getCandidateById(candidateId);

        // Assert
        assertNotNull(response);
        assertEquals("Neelam", response.getName());
        assertEquals("Senior Engineer", response.getDesignation());
        assertEquals("Noida", response.getLocation());
        assertEquals(60, response.getNoticePeriod());
        assertEquals("Java", response.getPrimarySkill());
        assertEquals("Spring Boot", response.getSecondarySkill());

        verify(candidateRepository, times(1)).findById(candidateId);
    }

    @Test
    public void testGetCandidateById_NotFound() {
        // Arrange
        Long candidateId = 2L;
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            candidateService.getCandidateById(candidateId);
        });

        assertEquals("Candidate  not found with id: 2", exception.getMessage());
        verify(candidateRepository, times(1)).findById(candidateId);
    }

}