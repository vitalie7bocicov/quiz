package ro.uaic.fii.CourseService.CourseService.dto.reqDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.uaic.fii.CourseService.CourseService.repository.model.Course;
import ro.uaic.fii.CourseService.CourseService.repository.model.Section;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicReqDto {
    @NotNull
    private Integer domainId;
    private Integer parentId;
    @NotBlank
    private String shortName;
    @NotBlank
    private String name;
    private String notes;
    @NotBlank
    private UUID userUid;
}
