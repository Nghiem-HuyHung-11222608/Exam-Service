package com.lms.exam.validator;

import com.lms.exam.constant.ValidatorMessage;
import com.lms.exam.dto.request.CreatedQuestion;
import com.lms.exam.dto.request.CreatedQuestionChoice;
import com.lms.exam.dto.request.UpdatedQuestion;
import com.lms.exam.dto.request.UpdatedQuestionChoice;
import com.lms.exam.exception.ValidationException;
import com.lms.exam.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

import static com.lms.exam.constant.QuestionType.SINGLE_CHOICE;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuestionValidator {

    private final MessageService messageService;

    public void validateQuestion(CreatedQuestion createdQuestion) {
        Objects.requireNonNull(createdQuestion, "createdQuestion is required");

        if (SINGLE_CHOICE.equals(createdQuestion.type())) {
            var choices = createdQuestion.choices();
            if (CollectionUtils.isEmpty(choices)) {
                return;
            }

            var correctChoices = choices.stream().filter(CreatedQuestionChoice::isCorrect).toList();
            int choiceNumber = correctChoices.size();
            if (choiceNumber != 1) {
                throw new ValidationException(messageService.getMessage(ValidatorMessage.QUESTION_SINGLE_CHOICE_NUMBER_MSG, choiceNumber));
            }
        }
    }

    public void validateQuestion(UpdatedQuestion updatedQuestion) {
        Objects.requireNonNull(updatedQuestion, "updatedQuestion is required");

        if (SINGLE_CHOICE.equals(updatedQuestion.type())) {
            var choices = updatedQuestion.choices();
            if (CollectionUtils.isEmpty(choices)) {
                return;
            }

            var correctChoices = choices.stream().filter(UpdatedQuestionChoice::isCorrect).toList();
            int choiceNumber = correctChoices.size();
            if (choiceNumber != 1) {
                throw new ValidationException(messageService.getMessage(ValidatorMessage.QUESTION_SINGLE_CHOICE_NUMBER_MSG, choiceNumber));
            }
        }
    }
}
