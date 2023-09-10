package ro.uaic.fii.SessionAggregator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record SessionDto(
        @NotNull(message = "domain id is required.")
        int domainId,
        @NotBlank(message = "name is required.")
        @Size(max = 100, message = "name max size is 100.")
        String name,
        Boolean active,
        String notes,
        UUID userUid) {

    @Override
    public int domainId() {
        return domainId;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Boolean active() {
        return active;
    }

    @Override
    public String notes() {
        return notes;
    }

    @Override
    public UUID userUid() {
        return userUid;
    }
}
