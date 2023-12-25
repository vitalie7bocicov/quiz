package ro.uaic.fii.SessionService.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record SessionDto(
        @NotNull(message = "domain id is required.")
        Integer domainId,
        @NotBlank(message = "name is required.")
        @Size(max = 100, message = "name max size is 100.")
        String name,
        Boolean active,
        String notes,
        UUID userUid) {
}
