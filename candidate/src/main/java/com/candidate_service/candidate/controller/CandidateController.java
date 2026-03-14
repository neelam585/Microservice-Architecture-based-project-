package com.candidate_service.candidate.controller;

import com.candidate_service.candidate.model.Candidate;
import com.candidate_service.candidate.payload.CandidateRequest;
import com.candidate_service.candidate.payload.CandidateResponse;
import com.candidate_service.candidate.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private Environment environment;

    @PostMapping("/candidate")
    public ResponseEntity<Candidate> createCandidate
            (@Valid @RequestBody CandidateRequest CandidateRequest){
        return new ResponseEntity<Candidate>(candidateService.createCandidate(CandidateRequest), HttpStatus.CREATED);
    }
    @GetMapping("/test")
    public String test() {
        return "candidate service working";
    }
    @GetMapping("/candidate")
    public ResponseEntity<List<CandidateResponse>> getAllCandidate(){
        return ResponseEntity.ok(candidateService.getAllCandidate());
    }

    @GetMapping("/candidate/{id}")
    public ResponseEntity<CandidateResponse> getCandidateById(@PathVariable Long id){
        Optional<CandidateResponse> candidateResponse = Optional.ofNullable(candidateService.getCandidateById(id));
        return candidateResponse.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());

    }
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }
}
