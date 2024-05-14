package ro.uaic.fii.CourseService.CourseService.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.uaic.fii.CourseService.CourseService.dto.reqDto.SectionReqDto;
import ro.uaic.fii.CourseService.CourseService.dto.resDto.SectionResDto;
import ro.uaic.fii.CourseService.CourseService.repository.model.Section;

@Mapper(componentModel = "spring")
public interface SectionMapper {

    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "updateUid", ignore = true)
    @Mapping(target = "insertUid", source = "userUid")
    Section toEntity(SectionReqDto createDto);

    SectionResDto toDto(Section section);
}
