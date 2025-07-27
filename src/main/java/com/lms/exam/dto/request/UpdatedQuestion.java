package com.lms.exam.dto.request;

import com.lms.exam.constant.QuestionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record UpdatedQuestion(

        String content,

        QuestionType type,

        @NotEmpty(message = "{question.choice.required}")
        @Valid
        List<UpdatedQuestionChoice> choices
) {
}
