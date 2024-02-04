package ro.uaic.fii.CourseService.CourseService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TopicDto(
        @NotNull Integer domainId,
        Integer parentId,
        @NotBlank String shortName,
        @NotBlank String name,
        String notes,
        UUID userUid
) { }
