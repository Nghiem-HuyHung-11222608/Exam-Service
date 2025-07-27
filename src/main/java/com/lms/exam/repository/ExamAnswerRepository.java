package com.lms.exam.repository;

import com.lms.exam.model.ExamAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamAnswerRepository extends JpaRepository<ExamAnswer, Long> {
    Optional<ExamAnswer> findByExamIdAndUserId(Long examId, String userId);
}