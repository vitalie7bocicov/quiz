package ro.uaic.fii.CourseService.CourseService.dto;

import jakarta.validation.constraints.NotNull;


public record CourseStudentGroupDto(
        @NotNull Integer courseId,
        @NotNull Integer studentGroupId
){ }
