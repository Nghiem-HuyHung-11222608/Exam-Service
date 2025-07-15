package com.lms.exam.dto.request;

import com.lms.exam.constant.QuestionType;

import java.util.List;

public record UpdatedQuestion(String content, QuestionType type, List<UpdatedQuestionChoice> choices) {
}
