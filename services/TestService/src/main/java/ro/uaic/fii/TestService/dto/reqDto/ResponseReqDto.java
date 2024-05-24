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
public class ResponseReqDto {
    @NotNull
    private Integer participantId;
    @NotNull
    private Integer orderNumber;
    private Integer baseQuestionId;
    @NotBlank
    private String question;
    @NotBlank
    private String answer;
    @NotNull
    private Double points;
    private String ipAddress;
    @NotNull
    private UUID userUid;
}
