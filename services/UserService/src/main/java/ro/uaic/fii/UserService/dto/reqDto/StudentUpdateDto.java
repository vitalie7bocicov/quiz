package ro.uaic.fii.UserService.dto.reqDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpdateDto {

    @NotNull(message = "Domain id must not be null")
    private Integer domainId;

    @NotNull(message = "Session id must not be null")
    private Integer sessionId;

    @NotNull(message = "Group must not be null")
    private Integer groupId;

    @NotBlank(message = "Code must not be empty")
    private String code;

    @NotBlank(message = "Account must not be empty")
    private String account;

    @NotBlank(message = "Password must not be empty")
    private String password;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @Email(message = "Email must be valid")
    private String email;
    private String notes;

    @NotNull(message = "Active must not be empty")
    private boolean active;

    @NotNull(message = "User uid must not be null")
    private UUID userUid;
}
