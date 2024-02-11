package ro.uaic.fii.CourseService.CourseService.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CourseInstructorDto(
        @NotNull Integer courseId,
        @NotNull UUID instructorId
){ }
