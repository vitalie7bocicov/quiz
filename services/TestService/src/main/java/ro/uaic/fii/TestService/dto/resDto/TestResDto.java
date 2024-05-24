package ro.uaic.fii.TestService.dto.resDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestResDto {
    private Integer id;
    private Integer courseId;
    private String name;
    private Integer groupId;
    private Timestamp startTime;
    private Integer duration;
    private Integer optNumber;
    private String accessCode;
    private boolean active;
    private boolean completed;
    private boolean demo;
    private String notes;
    private Date insertTimestamp;
    private Date updateTimestamp;
    private UUID insertUid;
    private UUID updateUid;
}
