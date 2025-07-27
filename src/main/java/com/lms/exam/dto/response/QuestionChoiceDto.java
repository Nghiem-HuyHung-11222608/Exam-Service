package com.lms.exam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionChoiceDto {
    private Long id;
    private String choice;
    private Boolean isCorrect;
}
