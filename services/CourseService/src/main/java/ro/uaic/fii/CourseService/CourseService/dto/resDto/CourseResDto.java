package ro.uaic.fii.CourseService.CourseService.dto.resDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.uaic.fii.CourseService.CourseService.repository.model.Topic;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResDto {
    private Integer id;
    private Integer domainId;
    private Integer sessionId;
    private String abbr;
    private String name;
    private String notes;
    private Date insertTimestamp;
    private Date updateTimestamp;
    private UUID insertUid;
    private UUID updateUid;
    private Set<Topic> topics = new HashSet<>();
    private Set<UUID> instructorIds = new HashSet<>();
    private Set<Integer> studentGroupIds = new HashSet<>();
}
