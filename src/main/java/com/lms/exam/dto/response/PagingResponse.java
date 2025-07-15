package com.lms.exam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagingResponse<T> {
    List<T> data;

    Integer page;
    Integer pageSize;
    Integer totalPage;
    Long totalRecord;
}
