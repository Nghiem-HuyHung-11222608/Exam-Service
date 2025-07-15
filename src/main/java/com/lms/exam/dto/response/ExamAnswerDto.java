package com.lms.exam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamAnswerDto {
    private Long id;
    private String userId;
    private Integer score;
    private List<ExamAnswerChoiceDto> choices;
    private LocalDateTime submittedAt;
}
