package com.interview_schedule_service.interview_schedule.repository;

import com.interview_schedule_service.interview_schedule.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewScheduleRepository extends JpaRepository<Interview, Long> {
}
