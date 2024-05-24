package ro.uaic.fii.TestService.dto.reqDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionReqDto {
    private Integer id;
    private Integer domainId;
    private String content;
    private boolean active;
    private String notes;
    private UUID userUid;
}
