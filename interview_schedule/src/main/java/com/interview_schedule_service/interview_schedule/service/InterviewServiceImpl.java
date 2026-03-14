package com.interview_schedule_service.interview_schedule.service;

import com.interview_schedule_service.interview_schedule.config.RestTemplateConfig;
import com.interview_schedule_service.interview_schedule.dto.InterviewWithCandidateDto;
import com.interview_schedule_service.interview_schedule.exception.ResourceNotFoundException;
import com.interview_schedule_service.interview_schedule.external.Candidate;
import com.interview_schedule_service.interview_schedule.model.Interview;
import com.interview_schedule_service.interview_schedule.payload.InterviewScheduleRequest;
import com.interview_schedule_service.interview_schedule.payload.InterviewScheduleResponse;
import com.interview_schedule_service.interview_schedule.repository.InterviewScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.util.MultiValueMap;
import org.springframework.util.LinkedMultiValueMap;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InterviewServiceImpl implements InterviewService{

    @Autowired
    private InterviewScheduleRepository interviewScheduleRepository;

    @Override
    public Interview scheduleInterview(InterviewScheduleRequest interviewScheduleRequest){
        Interview interviewSchedule = new Interview();
        interviewSchedule.setTechRating(interviewScheduleRequest.getTechRating());
        interviewSchedule.setHrRating(interviewScheduleRequest.getHrRating());
        interviewSchedule.setFinalStatus(interviewScheduleRequest.getFinalStatus());
        interviewSchedule.setCandidateId(interviewScheduleRequest.getCandidateId());
        return interviewScheduleRepository.save(interviewSchedule);
    }
    @Override
    public String cancelInterview(Long interviewScheduleId){
        Interview interview = getInterviewById(interviewScheduleId);
        //Interview interview = interviewScheduleRepository.findById(interviewScheduleId)
               // .orElseThrow(()->new ResourceNotFoundException("Interview ", "id", interviewScheduleId));

        try {
            interviewScheduleRepository.deleteById(interviewScheduleId);
            return "data has been deleted";
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Interview getInterviewById(Long interviewScheduleId){
        Interview interview = interviewScheduleRepository.findById(interviewScheduleId)
                .orElseThrow(()->new ResourceNotFoundException("Interview ", "id", interviewScheduleId));
    return interview;
    }

    @Override
    public Interview updateInterview(Long id, Interview updatedInterview){
        Interview interview = getInterviewById(id);
        interview.setHrRating(updatedInterview.getHrRating());
        interview.setFinalStatus(updatedInterview.getFinalStatus());
        interview.setCandidateId(updatedInterview.getCandidateId());
        return interviewScheduleRepository.save(interview);

    }
    @Override
    public List<InterviewScheduleResponse> getAllInterview(){
        List<Interview> interviews = interviewScheduleRepository.findAll();
        return interviews.stream().map(interview -> {
           InterviewScheduleResponse interviewResponse = new InterviewScheduleResponse();
           interviewResponse.setCandidateId(interview.getCandidateId());
           interviewResponse.setFinalStatus(interview.getFinalStatus());
           interviewResponse.setHrRating(interview.getHrRating());
           interviewResponse.setTechRating(interview.getTechRating());
           return interviewResponse;
        }).collect(Collectors.toList());
    }
    /*public List<InterviewWithCandidateDto> getAllInterview() {
        List<Interview> interviews = interviewScheduleRepository.findAll();
        List<InterviewWithCandidateDto> interviewWithCandidateDtos = new ArrayList<>();

        // Step 1: Get access token
       String token = getAccessToken(); // Implemented below

        RestTemplate restTemplate = new RestTemplate();

        for (Interview interview : interviews) {
            InterviewWithCandidateDto dto = new InterviewWithCandidateDto();
            dto.setInterview(interview);

            String url = "http://localhost:8080/candidate/candidate/" + interview.getCandidateId();

            // Step 2: Set Authorization header
            HttpHeaders headers = new HttpHeaders();
           // headers.set("Authorization", "Bearer " + token);
            headers.set("Authorization", "Bearer " + token);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<Candidate> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    Candidate.class
            );

            Candidate candidate = response.getBody();
            dto.setCandidate(candidate);
            interviewWithCandidateDtos.add(dto);
        }

        return interviewWithCandidateDtos;
    }




    public String getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "oauth2-pkce");
        //body.add("client_secret", "interview");
        //body.add("scope", "candidate.read panel.read");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "http://localhost:8090/realms/interview/protocol/openid-connect/token", request, Map.class);

        return response.getBody().get("access_token").toString();
    }*/



    /*@Override
    public List<InterviewWithCandidateDto> getAllInterview(){
        List<Interview> interviews = interviewScheduleRepository.findAll();
        List<InterviewWithCandidateDto> interviewWithCandidateDtos = new ArrayList<>();
        System.out.println("Candidate++++++++++++");
        //RestTemplate restTemplate = new RestTemplate();
        for(Interview interview: interviews) {
            InterviewWithCandidateDto interviewWithCandidateDto = new InterviewWithCandidateDto();
            interviewWithCandidateDto.setInterview(interview);
            String url = "http://localhost:8080/candidate/candidate/" +interview.getCandidateId() ;
            //Candidate candidate = restTemplate.getForObject(url, Candidate.class);
            //

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer ");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<Candidate> response = restTemplate.exchange(
                    //"http://localhost:8080/candidate/candidate/3",
                    url,
                    HttpMethod.GET,
                    entity,
                    Candidate.class
            );
            Candidate candidate = response.getBody();
            //
            interviewWithCandidateDto.setCandidate(candidate);
            interviewWithCandidateDtos.add(interviewWithCandidateDto);

        }

        return interviewWithCandidateDtos;
    }*/


    /*public String getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password");
        formData.add("client_id", "your-client-id");
        formData.add("username", "your-username");
        formData.add("password", "your-password");
        // formData.add("client_secret", "your-client-secret"); // if needed

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "http://localhost:8080/realms/your-realm/protocol/openid-connect/token",
                request,
                Map.class
        );

        Map<String, Object> responseBody = response.getBody();
        return (String) responseBody.get("access_token");
    }*/


}
