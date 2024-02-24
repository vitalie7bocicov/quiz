package ro.uaic.fii.TestService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record QuestionDto(
        Integer topicId,
        @NotNull Integer domainId,
        @NotBlank String content,
        @NotNull boolean active,
        String notes,
        @NotNull UUID userUuid
        ) {
}
