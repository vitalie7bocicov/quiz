package ro.uaic.fii.CourseService.CourseService.converter;

import ro.uaic.fii.CourseService.CourseService.dto.SectionDto;
import ro.uaic.fii.CourseService.CourseService.model.Section;

import java.util.UUID;

public class SectionDtoToModel {
    public static Section convert(SectionDto dto, UUID insertUuid, UUID updateUuid) {
        return new Section(dto.courseId(), dto.name(), dto.orderNumber(), dto.notes(), insertUuid, updateUuid);
    }
}
