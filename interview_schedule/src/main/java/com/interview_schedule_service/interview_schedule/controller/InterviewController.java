package com.interview_schedule_service.interview_schedule.controller;

import com.interview_schedule_service.interview_schedule.dto.InterviewWithCandidateDto;
import com.interview_schedule_service.interview_schedule.model.Interview;
import com.interview_schedule_service.interview_schedule.payload.InterviewScheduleRequest;
import com.interview_schedule_service.interview_schedule.payload.InterviewScheduleResponse;
import com.interview_schedule_service.interview_schedule.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interview")
public class InterviewController {
    @Autowired
    private InterviewService interviewService;

    @PostMapping("/schedule")
    public ResponseEntity<Interview> scheduleInterview(@RequestBody InterviewScheduleRequest interviewScheduleRequest){
        return new  ResponseEntity<>(interviewService.scheduleInterview(interviewScheduleRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> cancelInterview(@PathVariable Long id){
        return new ResponseEntity<>(interviewService.cancelInterview(id), HttpStatus.CREATED);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Interview> updateInterview(@PathVariable Long id, @RequestBody Interview requests){
        return new ResponseEntity<>(interviewService.updateInterview(id, requests), HttpStatus.OK);
    }
    @GetMapping("/schedule")
    public ResponseEntity<List<InterviewScheduleResponse>> getAllInterview(){

        return ResponseEntity.ok(interviewService.getAllInterview());

    }

    /*@GetMapping("/schedule")
    public ResponseEntity<List<InterviewWithCandidateDto>> getAllInterview(){
        System.out.println("Neelam+++++++++++");
        return ResponseEntity.ok(interviewService.getAllInterview());

    }*/


}
