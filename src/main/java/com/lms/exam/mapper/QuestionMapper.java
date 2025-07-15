package com.lms.exam.mapper;

import com.lms.exam.dto.request.CreatedQuestion;
import com.lms.exam.dto.request.UpdatedQuestion;
import com.lms.exam.dto.request.UpdatedQuestionChoice;
import com.lms.exam.dto.response.QuestionDto;
import com.lms.exam.model.Question;
import com.lms.exam.model.QuestionChoice;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionDto toQuestionDto(Question question);

    Question toQuestionModel(CreatedQuestion createdQuestion);

    @AfterMapping
    default void afterToQuestionModel(@MappingTarget Question newQuestion, CreatedQuestion createdQuestion) {
        var choices = newQuestion.getChoices();
        if (CollectionUtils.isEmpty(choices)) {
            return;
        }

        choices.forEach(questionChoice -> questionChoice.setQuestion(newQuestion));
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "choices", ignore = true)
    void toQuestionModel(@MappingTarget Question existingQuestion, UpdatedQuestion updatedQuestion);

    @AfterMapping
    default void afterToQuestionChoiceModel(@MappingTarget Question existingQuestion, UpdatedQuestion updatedQuestion) {
        var updatedChoices = updateQuestionChoiceModels(existingQuestion, updatedQuestion.choices());
        existingQuestion.getChoices().clear();
        existingQuestion.getChoices().addAll(updatedChoices);
    }

    default List<QuestionChoice> updateQuestionChoiceModels(Question existingQuestion, List<UpdatedQuestionChoice> updatedChoices) {
        var existingChoices = existingQuestion.getChoices();
        if (CollectionUtils.isEmpty(updatedChoices)) {
            return existingChoices;
        }

        // Partition to the updated choices to existing choices and new choices
        Map<Boolean, List<UpdatedQuestionChoice>> partitionedExistingChoiceMap = updatedChoices.stream()
                .collect(Collectors.partitioningBy(item -> Objects.nonNull(item.id())));

        // Merge new values for existing choices
        var updatedExistingChoices = partitionedExistingChoiceMap.get(Boolean.TRUE);
        var keepingExistingChoices = mergeUpdatedChoiceToExistingChoices(existingChoices, updatedExistingChoices);

        // Adding new choices: newChoices
        var newUpdatedChoices = partitionedExistingChoiceMap.get(Boolean.FALSE);
        var newChoices = toQuestionChoiceModels(newUpdatedChoices).stream()
                .peek(newChoice -> newChoice.setQuestion(existingQuestion))
                .toList();

        // Combine existing choices and new choices
        return Stream.concat(keepingExistingChoices.stream(), newChoices.stream())
                .collect(Collectors.toList());
    }

    List<QuestionChoice> toQuestionChoiceModels(List<UpdatedQuestionChoice> updatedQuestionChoices);

    default List<QuestionChoice> mergeUpdatedChoiceToExistingChoices(List<QuestionChoice> existingChoices, List<UpdatedQuestionChoice> updatedExistingChoices) {
        if (CollectionUtils.isEmpty(existingChoices) || CollectionUtils.isEmpty(updatedExistingChoices)) {
            return Collections.emptyList();
        }

        var updatedExistingChoiceMap = updatedExistingChoices.stream()
                .collect(Collectors.toMap(UpdatedQuestionChoice::id, Function.identity()));
        var updatedExistingChoiceIds = updatedExistingChoices.stream()
                .map(UpdatedQuestionChoice::id)
                .toList();

        return existingChoices.stream()
                .filter(item -> updatedExistingChoiceIds.contains(item.getId()))
                .peek(existingChoice ->
                        toQuestionChoiceModel(existingChoice, updatedExistingChoiceMap.get(existingChoice.getId())))
                .toList();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toQuestionChoiceModel(@MappingTarget QuestionChoice existingQuestionChoice, UpdatedQuestionChoice updatedQuestionChoice);
}
