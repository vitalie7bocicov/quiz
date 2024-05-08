package ro.uaic.fii.UserService.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.uaic.fii.UserService.dto.reqDto.StudentGroupCreateDto;
import ro.uaic.fii.UserService.dto.reqDto.StudentGroupUpdateDto;
import ro.uaic.fii.UserService.dto.resDto.StudentGroupResDto;
import ro.uaic.fii.UserService.repository.model.StudentGroup;

@Mapper(componentModel = "spring")
public interface StudentGroupMapper {

    StudentGroupResDto toDto(StudentGroup studentGroup);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "updateUid", ignore = true)
    @Mapping(target = "insertUid", source = "userUid")
    StudentGroup dtoToEntity(StudentGroupCreateDto createDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "insertUid", ignore = true)
    @Mapping(target = "updateUid", source = "userUid")
    StudentGroup dtoToEntity(StudentGroupUpdateDto updateDto);
}
