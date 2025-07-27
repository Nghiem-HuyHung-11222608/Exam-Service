package com.lms.exam.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@RequiredArgsConstructor
public class PagingFilter {
    private Integer page = 0;
    private Integer pageSize = 10;
    private String[] sort;
}
