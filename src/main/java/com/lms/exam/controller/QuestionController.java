package com.lms.exam.controller;

import com.lms.exam.constant.ResponseMessage;
import com.lms.exam.dto.request.CreatedQuestion;
import com.lms.exam.dto.request.QuestionFilter;
import com.lms.exam.dto.request.UpdatedQuestion;
import com.lms.exam.dto.response.PagingResponse;
import com.lms.exam.dto.response.QuestionDto;
import com.lms.exam.exception.NotFoundException;
import com.lms.exam.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @Operation(summary = "Create a new question")
    @PostMapping
    public QuestionDto createQuestion(@RequestBody CreatedQuestion createdQuestion) {
        return questionService.createQuestion(createdQuestion);
    }

    @Operation(summary = "Update a question")
    @PutMapping("/{id}")
    public QuestionDto updateQuestion(@PathVariable("id") Long questionId, @RequestBody UpdatedQuestion updatedQuestion) {
        return questionService.updateQuestion(questionId, updatedQuestion);
    }

    @Operation(summary = "Find a question by id")
    @GetMapping("/{id}")
    public QuestionDto findById(@PathVariable("id") Long questionId) {
        return questionService.findById(questionId)
                .orElseThrow(() ->
                        new NotFoundException("Not found question with id %s".formatted(questionId)));
    }

    @Operation(summary = "Find a all questions")
    @GetMapping
    public PagingResponse<QuestionDto> findAll(@ModelAttribute QuestionFilter questionFilter) {
        return questionService.findAll(questionFilter);
    }

    @Operation(summary = "Delete a question by Id")
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long questionId) {

        questionService.deleteById(questionId);

        return ResponseMessage.DELETE_SUCCESSFULLY;
    }
}
