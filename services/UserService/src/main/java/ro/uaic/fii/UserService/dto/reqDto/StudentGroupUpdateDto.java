package ro.uaic.fii.UserService.dto.reqDto;


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
public class StudentGroupUpdateDto {

    @NotNull(message = "DomainId cannot be null")
    private Integer domainId;
    @NotNull(message = "SessionId cannot be null")
    private Integer sessionId;
    private Integer parentGroupId;
    @NotBlank(message = "Abbr cannot be blank")
    @Size(min = 2, max = 50)
    private String abbr;
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50)
    private String name;
    private String notes;
    @NotNull(message = "UserUid cannot be null")
    private UUID userUid;
}
