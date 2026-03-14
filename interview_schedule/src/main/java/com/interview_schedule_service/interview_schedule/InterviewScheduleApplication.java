package com.interview_schedule_service.interview_schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class InterviewScheduleApplication {


	public static void main(String[] args) {
		SpringApplication.run(InterviewScheduleApplication.class, args);
	}

}
