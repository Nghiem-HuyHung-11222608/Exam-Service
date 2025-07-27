package com.lms.exam.dto.request;

import com.lms.exam.constant.QuestionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreatedQuestion(
        @NotBlank(message = "{question.content.required}")
        String content,

        @NotNull(message = "{question.type.required}")
        QuestionType type,

        @NotEmpty(message = "{question.choice.required}")
        @Valid
        List<CreatedQuestionChoice> choices
) {
}
