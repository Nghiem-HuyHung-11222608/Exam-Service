package com.lms.exam.repository;

import com.lms.exam.model.QuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionChoiceRepository extends JpaRepository<QuestionChoice, Long> {
}