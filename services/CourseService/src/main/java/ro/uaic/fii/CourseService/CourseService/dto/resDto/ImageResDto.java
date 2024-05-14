package ro.uaic.fii.CourseService.CourseService.dto.resDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageResDto {
    private Integer id;
    private Integer topicId;
    private String name;
    private  String mimeType;
    private byte[] content;
    private String notes;
    private Date insertTimestamp;
    private Date updateTimestamp;
    private UUID insertUid;
    private UUID updateUid;
}
