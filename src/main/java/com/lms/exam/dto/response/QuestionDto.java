package com.lms.exam.dto.response;

import com.lms.exam.constant.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private Long id;
    private String content;
    private QuestionType type;
    private List<QuestionChoiceDto> choices;
}