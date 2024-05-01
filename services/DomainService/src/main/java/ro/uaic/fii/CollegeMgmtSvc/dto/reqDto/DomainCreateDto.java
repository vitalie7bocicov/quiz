package ro.uaic.fii.CollegeMgmtSvc.dto.reqDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record DomainCreateDto(
        @NotBlank(message = "abbr is required.")
        @Size(max = 20, message = "abbr max size is 20.")
        String abbr,
        @NotBlank(message = "name is required.")
        @Size(max = 100, message = "name max size is 100.")
        String name,
        UUID userUid) { }
