package ro.uaic.fii.TestService.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ResponseDto (
        @NotNull Integer participantId,
        @NotNull Integer orderNumber,
        @NotNull Integer baseQuestionId,
        @NotNull String question,
        @NotNull String answer,
        @NotNull Double points,
        @NotNull UUID userUuid
        ){
}
