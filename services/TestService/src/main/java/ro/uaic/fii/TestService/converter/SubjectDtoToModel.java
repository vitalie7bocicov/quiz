package ro.uaic.fii.TestService.converter;

import ro.uaic.fii.TestService.dto.SubjectDto;
import ro.uaic.fii.TestService.model.Subject;

import java.util.UUID;

public class SubjectDtoToModel {
    public static Subject convert(SubjectDto dto, UUID insertUuid, UUID updateUuid) {
        return new Subject(dto.testId(),
                dto.sectionId(),
                dto.topicId(),
                dto.qsType(),
                dto.qsNumber(),
                dto.orderNumber(),
                insertUuid,
                updateUuid);
    }
}
