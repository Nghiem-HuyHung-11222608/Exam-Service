package com.lms.exam.mapper;

import com.lms.exam.dto.request.PagingFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

/**
 * Mapper for Paging Filter
 */
public interface PagingFilterMapper {

    /**
     * Parsing a Paging filter to a @{@link Pageable}
     *
     * @param pagingFilter The paging filter
     * @param defaultSort  The default sort
     * @return a @{@link Pageable}
     */
    static Pageable toPageable(PagingFilter pagingFilter, String[] defaultSort) {
        String[] sort = pagingFilter.getSort();
        if (Objects.isNull(sort)) {
            sort = defaultSort;
        }

        Sort.Direction direction = Sort.Direction.fromString(sort[1]);

        return PageRequest.of(pagingFilter.getPage(), pagingFilter.getPageSize(), Sort.by(direction, sort[0]));
    }
}
