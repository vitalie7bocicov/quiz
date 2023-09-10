package ro.uaic.fii.DomainService.convertor;

import ro.uaic.fii.DomainService.dto.DomainDto;
import ro.uaic.fii.DomainService.model.Domain;

public class DomainDtoToModel {
    public static Domain convert(DomainDto dto)
    {
        return new Domain(dto.abbr(), dto.name(), dto.userUid(), dto.userUid());
    }
}
