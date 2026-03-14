package com.candidate_service.candidate.service;

import com.candidate_service.candidate.exception.ResourceNotFoundException;
import com.candidate_service.candidate.model.Candidate;
import com.candidate_service.candidate.payload.CandidateRequest;
import com.candidate_service.candidate.repository.CandidateRepository;
import com.candidate_service.candidate.payload.CandidateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService{

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Candidate createCandidate(CandidateRequest candidateRequest){
        Candidate candidate = new Candidate();
        candidate.setName(candidateRequest.getName());
        candidate.setDesignation(candidateRequest.getDesignation());
        candidate.setLocation(candidateRequest.getLocation());
        candidate.setExperience(candidateRequest.getExperience());
        candidate.setQualification(candidateRequest.getQualification());
        candidate.setNoticePeriod(candidateRequest.getNoticePeriod());
        candidate.setPrimarySkill(candidateRequest.getPrimarySkill());
        candidate.setSecondarySkill(candidateRequest.getSecondarySkill());
        return candidateRepository.save(candidate);
    }

    @Override
    public List<CandidateResponse> getAllCandidate(){
        List<Candidate> candidateFromDb = candidateRepository.findAll();
        return candidateFromDb.stream().map(
                candidate -> {
                    CandidateResponse candidateResponse = new CandidateResponse();
                    candidateResponse.setName(candidate.getName());
                    candidateResponse.setDesignation(candidate.getDesignation());
                    candidateResponse.setExperience(candidate.getExperience());
                    candidateResponse.setLocation(candidate.getLocation());
                    candidateResponse.setNoticePeriod(candidate.getNoticePeriod());
                    candidateResponse.setPrimarySkill(candidate.getPrimarySkill());
                    candidateResponse.setSecondarySkill(candidate.getSecondarySkill());

                    return candidateResponse;
                }).collect(Collectors.toList());
    }
    @Override
    public CandidateResponse getCandidateById(Long id){
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Candidate ", "id", id));
        CandidateResponse candidateResponse = new CandidateResponse();
        candidateResponse.setName(candidate.getName());
        candidateResponse.setDesignation(candidate.getDesignation());
        candidateResponse.setNoticePeriod(candidate.getNoticePeriod());
        candidateResponse.setLocation(candidate.getLocation());
        candidateResponse.setPrimarySkill(candidate.getPrimarySkill());
        candidateResponse.setSecondarySkill(candidate.getSecondarySkill());
        return candidateResponse;

    }


}
