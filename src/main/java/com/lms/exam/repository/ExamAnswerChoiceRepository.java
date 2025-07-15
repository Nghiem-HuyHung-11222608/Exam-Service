package com.lms.exam.repository;

import com.lms.exam.model.ExamAnswerChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamAnswerChoiceRepository extends JpaRepository<ExamAnswerChoice, Long> {
}