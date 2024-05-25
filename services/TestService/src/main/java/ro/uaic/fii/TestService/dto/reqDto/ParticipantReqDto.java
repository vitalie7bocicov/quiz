package ro.uaic.fii.TestService.dto.reqDto;

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
public class ParticipantReqDto {
    @NotNull
    private Integer testId;
    private String ipAddress;
    private String locale;
    @NotNull
    private Double points;
    private boolean active;
    @NotNull
    private UUID userUid;
}
