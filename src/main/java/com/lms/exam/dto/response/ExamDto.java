package com.lms.exam.dto.response;

import com.lms.exam.constant.ExamType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto {
    private Long id;
    private String title;
    private Integer durationMinutes;
    private String location;
    private String slotTime;
    private ExamType type;
    private List<QuestionDto> questions;
    private Long lessonId;
    private List<ExamAnswerDto> examAnswers;
}