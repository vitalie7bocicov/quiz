package ro.uaic.fii.DomainService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record DomainDto(
        @NotBlank(message = "abbr is required.") @Size(max = 20, message = "abbr max size is 20.") String abbr,
        @NotBlank(message = "name is required.") @Size(max = 100, message = "name max size is 100.") String name,
        UUID userUid) {
    public DomainDto(String abbr, String name, UUID userUid) {
        this.abbr = abbr;
        this.name = name;
        this.userUid = userUid;
    }

    @Override
    public String abbr() {
        return abbr;
    }

    @Override
    public String name() {
        return name;
    }
}
