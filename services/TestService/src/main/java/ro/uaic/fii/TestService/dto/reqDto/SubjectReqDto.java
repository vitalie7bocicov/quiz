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
public class SubjectReqDto {
    @NotNull
    private Integer testId;
    @NotNull
    private Integer sectionId;
    @NotNull
    private Integer topicId;
    @NotNull
    private Character qsType;
    @NotNull
    private Integer qsNumber;
    @NotNull
    private Integer orderNumber;
    @NotNull
    private UUID userUid;
}
