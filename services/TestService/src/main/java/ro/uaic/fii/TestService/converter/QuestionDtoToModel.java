package ro.uaic.fii.TestService.converter;

import ro.uaic.fii.TestService.dto.QuestionDto;
import ro.uaic.fii.TestService.model.Question;

import java.util.UUID;

public class QuestionDtoToModel {
    public static Question convert(QuestionDto dto, UUID inserterUuid, UUID updaterUuid) {
        return new Question(dto.domainId(),
                dto.content(),
                dto.active(),
                dto.notes(),
                inserterUuid,
                updaterUuid);
    }
}
