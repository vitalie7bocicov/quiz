package ro.uaic.fii.ImageService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ImageDto(
        @NotNull Integer topicId,
        @NotBlank String name,
        @NotBlank String mimeType,
        byte[] content,
        String notes,
        @NotNull UUID userUid
) {}
