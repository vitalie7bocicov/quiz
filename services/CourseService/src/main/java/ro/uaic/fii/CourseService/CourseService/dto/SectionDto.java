package ro.uaic.fii.CourseService.CourseService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SectionDto(
        @NotNull Integer courseId,
        @NotBlank String name,
        Integer orderNumber,
        String notes,
        UUID userUid
) { }