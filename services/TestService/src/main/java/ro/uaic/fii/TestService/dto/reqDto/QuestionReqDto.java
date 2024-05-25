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
public class QuestionReqDto {
    @NotNull
    private Integer domainId;
    @NotBlank
    private String content;
    private boolean active;
    private String notes;
    @NotNull
    private UUID userUid;
}
