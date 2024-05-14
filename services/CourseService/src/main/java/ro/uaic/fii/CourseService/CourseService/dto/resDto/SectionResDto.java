package ro.uaic.fii.CourseService.CourseService.dto.resDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.uaic.fii.CourseService.CourseService.repository.model.Topic;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectionResDto {
    private Integer id;
    private Integer courseId;
    private String name;
    private Integer orderNumber;
    private String notes;
    private Date insertTimestamp;
    private Date updateTimestamp;
    private UUID updateUid;
    private UUID insertUid;
    private Set<Topic> topics;
}
