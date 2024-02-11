package ro.uaic.fii.CourseService.CourseService.dto;

import jakarta.validation.constraints.NotNull;

public record SectionTopicDto(
        @NotNull Integer sectionId,
        @NotNull Integer topicId
){ }
