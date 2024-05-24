package ro.uaic.fii.TestService.dto.resDto;

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
public class ResponseResDto {
    private Integer id;
    private Integer participantId;
    private Integer orderNumber;
    private Integer baseQuestionId;
    private String question;
    private String answer;
    private Double points;
    private String ipAddress;
    private Date insertTimestamp;
    private Date updateTimestamp;
    private UUID insertUid;
    private UUID updateUid;
}
