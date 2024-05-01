package ro.uaic.fii.CollegeMgmtSvc.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.SessionCreateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.SessionUpdateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.resDto.SessionDto;
import ro.uaic.fii.CollegeMgmtSvc.repository.model.Session;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "updateUid", ignore = true)
    @Mapping(target = "insertUid", source = "userUid")
    Session dtoToEntity(SessionCreateDto createDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "insertUid", ignore = true)
    @Mapping(target = "updateUid", source = "userUid")
    Session dtoToEntity(SessionUpdateDto updateDto);

    SessionDto toDto(Session entity);
}
