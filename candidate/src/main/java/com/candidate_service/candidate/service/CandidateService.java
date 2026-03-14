package com.candidate_service.candidate.service;

import com.candidate_service.candidate.model.Candidate;
import com.candidate_service.candidate.payload.CandidateRequest;
import com.candidate_service.candidate.payload.CandidateResponse;

import java.util.List;

public interface CandidateService {
    Candidate createCandidate(CandidateRequest candidateRequest);
    List<CandidateResponse> getAllCandidate();
    CandidateResponse getCandidateById(Long id);
}
