package ro.uaic.fii.TestService.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.uaic.fii.TestService.dto.reqDto.ParticipantReqDto;
import ro.uaic.fii.TestService.dto.resDto.ParticipantResDto;
import ro.uaic.fii.TestService.repository.model.Participant;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "updateUid", ignore = true)
    @Mapping(target = "insertUid", source = "userUid")
    Participant dtoToEntity(ParticipantReqDto dto);

    ParticipantResDto toDto(Participant participant);
}
