package ro.uaic.fii.TestService.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ParticipantDto(
        @NotNull Integer testId,
        Double points,
        boolean active,
        UUID userUuid
) {
}
