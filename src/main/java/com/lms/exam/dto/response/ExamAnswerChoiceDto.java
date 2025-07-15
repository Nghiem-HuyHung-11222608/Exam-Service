package com.lms.exam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamAnswerChoiceDto {
    private Long id;
    private QuestionDto question;
    private QuestionChoiceDto choice;
}
