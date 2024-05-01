package ro.uaic.fii.CollegeMgmtSvc.dto.resDto;

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
public class SessionDto {
    private Integer id;
    private Integer domainId;
    private String name;
    private boolean active;
    private String notes;
    private Date insertTimestamp;
    private Date updateTimestamp;
    private UUID insertUid;
    private UUID updateUid;
}
