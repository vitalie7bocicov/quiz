package ro.uaic.fii.CourseService.CourseService.converter;

import ro.uaic.fii.CourseService.CourseService.dto.CourseDto;
import ro.uaic.fii.CourseService.CourseService.model.Course;

import java.util.UUID;

public class CourseDtoToModel {
    public static Course convert(CourseDto dto, UUID inserterUuid, UUID updaterUuid) {
        return new Course(dto.domainId(),
                dto.sessionId(),
                dto.abbr(),
                dto.name(),
                dto.notes(),
                inserterUuid,
                updaterUuid);
    }
}
