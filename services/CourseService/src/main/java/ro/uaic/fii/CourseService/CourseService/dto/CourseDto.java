package ro.uaic.fii.CourseService.CourseService.dto;


import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CourseDto(
        @NotNull Integer domainId,
        @NotNull Integer sessionId,
        String abbr,
        @NotNull String name,
        String notes,
        @NotNull UUID userUid
){};

