package com.lms.exam.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatedQuestionChoice(
        @NotBlank(message = "{questionChoice.choice.required}")
        String choice,

        @NotNull(message = "{questionChoice.isCorrect.required}")
        Boolean isCorrect
) {
}
