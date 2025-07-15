package com.lms.exam.service;

import com.lms.exam.dto.request.CreatedQuestion;
import com.lms.exam.dto.request.QuestionFilter;
import com.lms.exam.dto.request.UpdatedQuestion;
import com.lms.exam.dto.response.PagingResponse;
import com.lms.exam.dto.response.QuestionDto;

import java.util.Optional;

public interface QuestionService {
    QuestionDto createQuestion(CreatedQuestion createdQuestion);

    QuestionDto updateQuestion(Long questionId, UpdatedQuestion updatedQuestion);

    Optional<QuestionDto> findById(Long questionId);

    PagingResponse<QuestionDto> findAll(QuestionFilter questionFilter);

    void deleteById(Long questionId);
}
