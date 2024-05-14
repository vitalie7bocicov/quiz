package ro.uaic.fii.CourseService.CourseService.dto.reqDto;

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
public class SectionReqDto {
    @NotNull
    private Integer courseId;
    @NotNull
    private String name;
    private Integer orderNumber;
    private String notes;
    @NotNull
    private UUID userUid;
}
