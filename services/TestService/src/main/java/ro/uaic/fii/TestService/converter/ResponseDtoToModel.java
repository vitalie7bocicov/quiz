package ro.uaic.fii.TestService.converter;

import ro.uaic.fii.TestService.dto.ResponseDto;
import ro.uaic.fii.TestService.model.Response;

import java.util.UUID;

public class ResponseDtoToModel {
    public static Response convert(ResponseDto dto, String ipAddress, String locale, UUID inserterUuid, UUID updaterUuid) {
        return new Response(
                dto.participantId(),
                dto.orderNumber(),
                dto.baseQuestionId(),
                dto.question(),
                dto.answer(),
                dto.points(),
                ipAddress,
                inserterUuid,
                updaterUuid
        );
    }
}
