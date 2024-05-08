package ro.uaic.fii.UserService.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.uaic.fii.UserService.dto.reqDto.InstructorCreateDto;
import ro.uaic.fii.UserService.dto.reqDto.InstructorUpdateDto;
import ro.uaic.fii.UserService.dto.resDto.InstructorResDto;
import ro.uaic.fii.UserService.repository.model.Instructor;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

    InstructorResDto toDto(Instructor instructor);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "updateUid", ignore = true)
    @Mapping(target = "insertUid", source = "userUid")
    Instructor dtoToEntity(InstructorCreateDto createDto);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "insertUid", ignore = true)
    @Mapping(target = "updateUid", source = "userUid")
    Instructor dtoToEntity(InstructorUpdateDto updateDto);
}
