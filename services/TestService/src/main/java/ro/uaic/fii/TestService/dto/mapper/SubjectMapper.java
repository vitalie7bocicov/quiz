package ro.uaic.fii.TestService.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.uaic.fii.TestService.dto.reqDto.SubjectReqDto;
import ro.uaic.fii.TestService.dto.resDto.SubjectResDto;
import ro.uaic.fii.TestService.repository.model.Subject;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "updateUid", ignore = true)
    @Mapping(target = "insertUid", source = "userUid")
    Subject dtoToEntity(SubjectReqDto dto);

    SubjectResDto toDto(Subject subject);
}
