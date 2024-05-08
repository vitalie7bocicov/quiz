package ro.uaic.fii.UserService.dto.reqDto;

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
public class InstructorUpdateDto {
    @NotNull(message = "Domain id cannot be null")
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
}
