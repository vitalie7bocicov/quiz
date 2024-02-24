package ro.uaic.fii.TestService.converter;

import ro.uaic.fii.TestService.dto.ParticipantDto;
import ro.uaic.fii.TestService.model.Participant;

import java.util.UUID;

public class ParticipantDtoToModel {
    public static Participant convert(ParticipantDto participantDto, String ipAddress, String locale, UUID inserterUuid, UUID updaterUuid) {
        return new Participant(
                participantDto.testId(),
                ipAddress,
                locale,
                participantDto.points(),
                participantDto.active(),
                inserterUuid,
                updaterUuid
        );
    }
}
