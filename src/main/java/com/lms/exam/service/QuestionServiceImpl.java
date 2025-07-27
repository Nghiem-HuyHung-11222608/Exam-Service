package com.lms.exam.service;

import com.lms.exam.dto.request.CreatedQuestion;
import com.lms.exam.dto.request.QuestionFilter;
import com.lms.exam.dto.request.UpdatedQuestion;
import com.lms.exam.dto.response.PagingResponse;
import com.lms.exam.dto.response.QuestionDto;
import com.lms.exam.exception.NotFoundException;
import com.lms.exam.mapper.PagingFilterMapper;
import com.lms.exam.mapper.QuestionMapper;
import com.lms.exam.repository.QuestionRepository;
import com.lms.exam.repository.specs.QuestionSpecification;
import com.lms.exam.validator.QuestionValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final QuestionValidator questionValidator;

    @Override
    @Transactional
    public QuestionDto createQuestion(CreatedQuestion createdQuestion) {
        log.info("Creating new question {}", createdQuestion);

        questionValidator.validateQuestion(createdQuestion);

        var question = questionMapper.toQuestionModel(createdQuestion);
        question = questionRepository.save(question);

        return questionMapper.toQuestionDto(question);
    }

    @Override
    @Transactional
    public QuestionDto updateQuestion(Long questionId, UpdatedQuestion updatedQuestion) {
        log.info("Updating a question with id {} and new value {}", questionId, updatedQuestion);

        var question = questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Not found question with id %s".formatted(questionId)));

        questionValidator.validateQuestion(updatedQuestion);

        // Update question, update or add new question choices
        questionMapper.toQuestionModel(question, updatedQuestion);
        question = questionRepository.save(question);

        return questionMapper.toQuestionDto(question);
    }

    @Override
    public Optional<QuestionDto> findById(Long questionId) {
        log.info("Finding a question by Id {}", questionId);

        return questionRepository.findById(questionId)
                .map(questionMapper::toQuestionDto);
    }

    @Override
    public PagingResponse<QuestionDto> findAll(QuestionFilter questionFilter) {
        log.info("Searching questions by filter {}", questionFilter);

        String[] defaultSort = {"type", "asc"};
        var pageable = PagingFilterMapper.toPageable(questionFilter, defaultSort);
        var questionSpec = new QuestionSpecification(questionFilter);

        var pagingQuestion = questionRepository.findAll(questionSpec, pageable);
        var questions = pagingQuestion.stream()
                .map(questionMapper::toQuestionDto)
                .toList();

        return PagingResponse.<QuestionDto>builder()
                .data(questions)
                .page(questionFilter.getPage())
                .pageSize(questionFilter.getPageSize())
                .totalPage(pagingQuestion.getTotalPages())
                .totalRecord(pagingQuestion.getTotalElements())
                .build();
    }

    @Override
    public void deleteById(Long questionId) {
        log.info("Deleting a question by Id {}", questionId);

        questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Not found question with id %s".formatted(questionId)));

        questionRepository.deleteById(questionId);
    }
}
