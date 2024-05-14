package ro.uaic.fii.CourseService.CourseService.dto.resDto;

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
public class TopicResDto {
    private Integer id;
    private Integer domainId;
    private Integer parentId;
    private String shortName;
    private String name;
    private String notes;
    private Date insertTimestamp;
    private Date updateTimestamp;
    private UUID insertUid;
    private UUID updateUid;
    private Set<Course> courses = new HashSet<>();
    private Set<Section> sections = new HashSet<>();
}
