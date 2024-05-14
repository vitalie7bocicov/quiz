package ro.uaic.fii.CourseService.CourseService.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.uaic.fii.CourseService.CourseService.dto.reqDto.ImageReqDto;
import ro.uaic.fii.CourseService.CourseService.dto.resDto.ImageResDto;
import ro.uaic.fii.CourseService.CourseService.repository.model.Image;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "updateUid", ignore = true)
    @Mapping(target = "insertUid", source = "userUid")
    Image toEntity(ImageReqDto imageReqDto);

    ImageResDto toDto(Image image);
}
