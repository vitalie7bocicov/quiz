package ro.uaic.fii.UserService.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class StudentGroupDto {

    @NotNull
    private Integer domainId;
    @NotNull
    private Integer sessionId;
    private Integer parentGroupId;
    @NotBlank
    @Size(min = 2, max = 50)
    private String abbr;
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    private String notes;
    private UUID userUid;

    public Integer getDomainId() {
        return domainId;
    }
    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }
    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getParentGroupId() {
        return parentGroupId;
    }

    public void setParentGroupId(Integer parentGroupId) {
        this.parentGroupId = parentGroupId;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUserUid() {
        return userUid;
    }

    public void setUserUid(UUID userUid) {
        this.userUid = userUid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
