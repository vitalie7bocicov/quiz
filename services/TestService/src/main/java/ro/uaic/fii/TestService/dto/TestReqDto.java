package ro.uaic.fii.TestService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.UUID;

public record TestReqDto (
        @NotNull Integer courseId,
        @NotBlank String name,
        Integer groupId,
        @NotNull Timestamp startTime,
        @NotNull Integer duration,
        @NotNull Integer optNumber,
        @NotBlank String accessCode,
        @NotNull boolean active,
        @NotNull boolean completed,
        @NotNull boolean demo,
        String notes,
        @NotNull UUID userUid
){ }
