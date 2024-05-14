package ro.uaic.fii.CourseService.CourseService.dto.reqDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageReqDto {
    @NotNull
    private Integer topicId;
    @NotBlank
    private String name;
    private String mimeType;
    private byte[] content;
    private String notes;
    @NotNull
    private UUID userUid;
}
