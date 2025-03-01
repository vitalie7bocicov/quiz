package ro.uaic.fii.UserService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull
    private Integer domainId;
    @NotBlank
    private String role;
    @NotBlank
    @Size(min = 5, max = 20)
    private String account;

    @NotBlank
    @Size(min = 6, max = 30)
    private String password;
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank
    @Email
    private String email;

    private String notes;

    private boolean active;

    private UUID userUid;
}
