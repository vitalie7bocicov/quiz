package ro.uaic.fii.CourseService.CourseService.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.uaic.fii.CourseService.CourseService.dto.reqDto.TopicReqDto;
import ro.uaic.fii.CourseService.CourseService.dto.resDto.TopicResDto;
import ro.uaic.fii.CourseService.CourseService.repository.model.Topic;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    @Mapping(target = "sections", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "updateUid", ignore = true)
    @Mapping(target = "insertUid", source = "userUid")
    Topic dtoToEntity(TopicReqDto dto);

    TopicResDto toDto(Topic entity);
}
