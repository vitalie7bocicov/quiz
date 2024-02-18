package ro.uaic.fii.TestService.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SubjectDto(
        @NotNull Integer testId,
        @NotNull Integer sectionId,
        Integer topicId,
        Character qsType,
        @NotNull Integer qsNumber,
        @NotNull Integer orderNumber,
        @NotNull UUID userUuid
        ) {
}
