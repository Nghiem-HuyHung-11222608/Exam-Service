package com.lms.exam.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatedQuestionChoice(
        Long id,

        @NotBlank(message = "{questionChoice.choice.required}")
        String choice,

        @NotNull(message = "{questionChoice.isCorrect.required}")
        Boolean isCorrect
) {
}
