package com.lms.exam.service;

import com.lms.exam.dto.response.ExamDto;
import com.lms.exam.dto.response.ExamAnswerDto;
import com.lms.exam.dto.response.QuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    @Override
    public ExamDto createExam(ExamDto dto) {
        return null;
    }

    @Override
    public ExamDto getExam(Long id) {
        return null;
    }

    @Override
    public List<ExamDto> getAllExams() {
        return List.of();
    }

    @Override
    public void deleteExam(Long id) {

    }

    @Override
    public QuestionDto addQuestion(Long examId, QuestionDto qdto) {
        return null;
    }

    @Override
    public void deleteQuestion(Long questionId) {

    }

    @Override
    public ExamAnswerDto submitExamResult(Long examId, String userId, List<Integer> userAnswers) {
        return null;
    }

    @Override
    public ExamAnswerDto getExamResult(Long examId, String userId) {
        return null;
    }

    @Override
    public ExamDto getExamByLessonId(Long lessonId) {
        return null;
    }
}
