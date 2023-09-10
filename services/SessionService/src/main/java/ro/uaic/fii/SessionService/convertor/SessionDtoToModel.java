package ro.uaic.fii.SessionService.convertor;

import ro.uaic.fii.SessionService.dto.SessionDto;
import ro.uaic.fii.SessionService.model.Session;

public class SessionDtoToModel {
    public static Session convert(SessionDto dto)
    {
        return new Session(dto.domainId(), dto.name(), dto.active(), dto.notes(), dto.userUid(), dto.userUid());
    }
}
