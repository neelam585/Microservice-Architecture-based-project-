package com.interview_schedule_service.interview_schedule.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewScheduleResponse {
    private Long candidateId;
    private String techRating;
    private String hrRating;
    private Boolean finalStatus;
}
