package ro.uaic.fii.TestService.dto.reqDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestReqDto {
    @NotNull
    private Integer courseId;
    @NotBlank
    private String name;
    @NotNull
    private Integer groupId;
    @NotNull
    private Timestamp startTime;
    @NotNull
    private Integer duration;
    @NotNull
    private Integer optNumber;
    @NotBlank
    private String accessCode;
    @NotNull
    private boolean active;
    private boolean demo;
    private boolean completed;
    private String notes;
    @NotNull
    private UUID userUid;
}