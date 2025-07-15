package com.lms.exam.dto.request;

import com.lms.exam.constant.QuestionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Data
@RequiredArgsConstructor
public class QuestionFilter extends PagingFilter {
    private String content;
    private QuestionType type;
}
