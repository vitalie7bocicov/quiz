package ro.uaic.fii.CourseService.CourseService.dto.reqDto;

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
public class CourseReqDto {
    @NotNull
    private Integer domainId;
    @NotNull
    private Integer sessionId;
    private String abbr;
    @NotBlank
    private String name;
    private String notes;
    @NotNull
    private UUID userUid;
}
