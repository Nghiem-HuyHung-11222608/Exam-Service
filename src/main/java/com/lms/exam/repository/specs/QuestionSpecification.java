package com.lms.exam.repository.specs;

import com.lms.exam.constant.QuestionType;
import com.lms.exam.dto.request.QuestionFilter;
import com.lms.exam.model.Question;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionSpecification implements Specification<Question> {

    private final QuestionFilter questionFilter;

    public QuestionSpecification(QuestionFilter questionFilter) {
        this.questionFilter = questionFilter;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        String content = questionFilter.getContent();
        if (StringUtils.isNotEmpty(content)) {
            predicates.add(cb.like(cb.lower(root.get("content")), "%" + content.toLowerCase() + "%"));
        }

        QuestionType type = questionFilter.getType();
        if (Objects.nonNull(type)) {
            predicates.add(cb.equal(root.get("type"), type));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
