package ro.uaic.fii.CourseService.CourseService.dto.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.uaic.fii.CourseService.CourseService.dto.reqDto.CourseReqDto;
import ro.uaic.fii.CourseService.CourseService.dto.resDto.CourseResDto;
import ro.uaic.fii.CourseService.CourseService.repository.model.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "updateUid", ignore = true)
    @Mapping(target = "insertUid", source = "userUid")
    Course dtoToEntity(CourseReqDto createDto);

    CourseResDto toDto(Course course);
}
