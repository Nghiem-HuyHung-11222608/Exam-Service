package com.lms.exam.mapper;

import com.lms.exam.dto.response.ExamDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExamMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static com.lms.exam.model.Exam toEntity(ExamDto dto) {
        com.lms.exam.model.Exam exam = new com.lms.exam.model.Exam();
        exam.setId(dto.getId());
        exam.setTitle(dto.getTitle());
        exam.setDurationMinutes(dto.getDurationMinutes());
        exam.setLocation(dto.getLocation());
        exam.setSlotTime(LocalDateTime.parse(dto.getSlotTime(), FORMATTER));
        return exam;
    }

    public static ExamDto toDto(com.lms.exam.model.Exam entity) {
        ExamDto dto = new ExamDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDurationMinutes(entity.getDurationMinutes());
        dto.setLocation(entity.getLocation());
        dto.setSlotTime(entity.getSlotTime().format(FORMATTER));
        return dto;
    }
}
