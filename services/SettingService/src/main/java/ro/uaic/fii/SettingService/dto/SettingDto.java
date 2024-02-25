package ro.uaic.fii.SettingService.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SettingDto (
        @NotNull String value,
        @NotNull UUID userUuid
){
}
