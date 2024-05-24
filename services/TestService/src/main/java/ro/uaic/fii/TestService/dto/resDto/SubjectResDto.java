package ro.uaic.fii.TestService.dto.resDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResDto {
    private Integer id;
    private Integer testId;
    private Integer sectionId;
    private Integer topicId;
    private Character qsType;
    private Integer qsNumber;
    private Integer orderNumber;
    private Date insertTimestamp;
    private Date updateTimestamp;
    private UUID insertUid;
    private UUID updateUid;
}
