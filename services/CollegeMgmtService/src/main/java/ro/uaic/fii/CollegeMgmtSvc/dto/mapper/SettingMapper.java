package ro.uaic.fii.CollegeMgmtSvc.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.SettingCreateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.SettingUpdateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.resDto.SettingDto;
import ro.uaic.fii.CollegeMgmtSvc.repository.model.Setting;

@Mapper(componentModel = "spring")
public interface SettingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "updateUid", ignore = true)
    @Mapping(target = "insertUid", source = "userUid")
    Setting dtoToEntity(SettingCreateDto createDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "insertUid", ignore = true)
    @Mapping(target = "updateUid", source = "userUid")
    Setting dtoToEntity(SettingUpdateDto updateDto);

    SettingDto toDto(Setting entity);
}
