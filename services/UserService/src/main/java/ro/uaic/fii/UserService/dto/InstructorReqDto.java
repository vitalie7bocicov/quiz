package ro.uaic.fii.UserService.dto;

import jakarta.validation.constraints.*;

import java.util.UUID;

public class InstructorReqDto {

    @NotNull(message = "DomainId cannot be null")
    private Integer domainId;
    @NotBlank(message = "Account cannot be blank")
    @Size(min = 5, max = 20)
    private String account;

    @NotBlank
    @Size(min = 6, max = 30)
    private String password;
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50)
    private String name;
    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;
    private String notes;
    private boolean active;

    @NotNull(message = "UserUid cannot be null")
    private UUID userUid;

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UUID getUserUid() {
        return userUid;
    }

    public void setUserUid(UUID userUid) {
        this.userUid = userUid;
    }
}
