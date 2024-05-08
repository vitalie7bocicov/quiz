package ro.uaic.fii.UserService.dto.resDto;

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
public class StudentResDto {
    private UUID id;
    private Integer domainId;
    private Integer sessionId;
    private Integer groupId;
    private String code;
    private String account;
    private String name;
    private String email;
    private String notes;
    private boolean active;
    private Date insertTimestamp;
    private Date updateTimestamp;
    private UUID insertUid;
    private UUID updateUid;
}
