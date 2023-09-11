package ro.uaic.fii.UserService.dto;

import java.util.UUID;

public class InstructorResDto {

    private final UUID id;
    private final Integer domainId;
    private final String account;
    private final String name;
    private final String email;
    private final String notes;
    private final boolean active;

    public InstructorResDto(UUID id,
                            Integer domainId,
                            String account,
                            String name,
                            String email,
                            String notes,
                            boolean active) {
        this.id = id;
        this.domainId = domainId;
        this.account = account;
        this.name = name;
        this.email = email;
        this.notes = notes;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public String getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNotes() {
        return notes;
    }

    public boolean getActive() {
        return active;
    }
}
