package ro.uaic.fii.UserService.dto.resDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class InstructorResDto {

    private final UUID id;
    private final Integer domainId;
    private final String account;
    private final String name;
    private final String email;
    private final String notes;
    private final boolean active;
    private Date insertTimestamp;
    private Date updateTimestamp;
    private UUID insertUid;
    private UUID updateUid;
}
