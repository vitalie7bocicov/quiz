package ro.uaic.fii.UserService.dto.resDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentGroupResDto {
    private Integer id;
    private Integer domainId;
    private Integer sessionId;
    private Integer parentId;
    private String abbr;
    private String name;
    private String notes;
    private Date insertTimestamp;
    private Date updateTimestamp;
    private UUID insertUid;
    private UUID updateUid;
}

