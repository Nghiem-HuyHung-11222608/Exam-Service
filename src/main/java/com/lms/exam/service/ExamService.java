package com.lms.exam.service;

import com.lms.exam.dto.response.ExamDto;
import com.lms.exam.dto.response.ExamAnswerDto;
import com.lms.exam.dto.response.QuestionDto;

import java.util.List;

public interface ExamService {
    ExamDto createExam(ExamDto dto);
    ExamDto getExam(Long id);
    List<ExamDto> getAllExams();
    void deleteExam(Long id);
    QuestionDto addQuestion(Long examId, QuestionDto qdto);
    void deleteQuestion(Long questionId);
    ExamAnswerDto submitExamResult(Long examId, String userId, List<Integer> userAnswers);
    ExamAnswerDto getExamResult(Long examId, String userId);
    ExamDto getExamByLessonId(Long lessonId);
}