package ro.uaic.fii.CollegeMgmtSvc.dto.reqDto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record SettingUpdateDto (
        @NotBlank(message = "value is required.")
        String value,
        UUID userUid) { }
