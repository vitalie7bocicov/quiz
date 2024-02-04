package ro.uaic.fii.CourseService.CourseService.converter;

import ro.uaic.fii.CourseService.CourseService.dto.TopicDto;
import ro.uaic.fii.CourseService.CourseService.model.Topic;

import java.util.UUID;

public class TopicDtoToModel {
    public static Topic convert(TopicDto dto, UUID inserterUuid, UUID updaterUuid) {
        return new Topic(dto.domainId(),
                dto.parentId(),
                dto.shortName(),
                dto.name(),
                dto.notes(),
                inserterUuid,
                updaterUuid);
    }
}
