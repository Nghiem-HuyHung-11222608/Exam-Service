package com.lms.exam.dto.request;

import com.lms.exam.constant.QuestionType;

import java.util.List;

public record CreatedQuestion(String content, QuestionType type, List<CreatedQuestionChoice> choices) {
}
