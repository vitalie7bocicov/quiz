package ro.uaic.fii.SessionService.convertor;

import ro.uaic.fii.SessionService.dto.SessionDto;
import ro.uaic.fii.SessionService.model.Session;

import java.util.UUID;

public class SessionDtoToModel {
    public static Session convert(SessionDto dto, UUID insertUid, UUID updateUid)
    {
        return new Session(dto.domainId(),
                dto.name(),
                dto.active(),
                dto.notes(),
                insertUid,
                updateUid);
    }
}
