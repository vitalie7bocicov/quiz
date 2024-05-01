package ro.uaic.fii.CollegeMgmtSvc.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.DomainCreateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.DomainUpdateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.resDto.DomainDto;
import ro.uaic.fii.CollegeMgmtSvc.repository.model.Domain;

@Mapper(componentModel = "spring")
public interface DomainMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "updateUid", ignore = true)
    @Mapping(target = "insertUid", source = "userUid")
    Domain dtoToEntity(DomainCreateDto createDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "insertUid", ignore = true)
    @Mapping(target = "updateUid", source = "userUid")
    Domain dtoToEntity(DomainUpdateDto updateDto);

    DomainDto toDto(Domain domain);
}
