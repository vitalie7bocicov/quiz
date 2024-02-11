package ro.uaic.fii.CourseService.CourseService.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CourseTopicDto(
        @NotNull Integer courseId,
        @NotNull Integer topicId
){ }
