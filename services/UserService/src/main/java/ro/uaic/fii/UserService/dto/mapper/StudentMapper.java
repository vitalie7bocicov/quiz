package ro.uaic.fii.UserService.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.uaic.fii.UserService.dto.reqDto.StudentCreateDto;
import ro.uaic.fii.UserService.dto.reqDto.StudentUpdateDto;
import ro.uaic.fii.UserService.dto.resDto.StudentResDto;
import ro.uaic.fii.UserService.repository.model.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

   StudentResDto toDto(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "updateUid", ignore = true)
    @Mapping(target = "insertUid", source = "userUid")
    Student dtoToEntity(StudentCreateDto createDto);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "insertUid", ignore = true)
    @Mapping(target = "updateUid", source = "userUid")
    Student dtoToEntity(StudentUpdateDto updateDto);
}
