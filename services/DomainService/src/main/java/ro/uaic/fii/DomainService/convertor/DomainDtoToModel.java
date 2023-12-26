package ro.uaic.fii.DomainService.convertor;

import ro.uaic.fii.DomainService.dto.DomainDto;
import ro.uaic.fii.DomainService.model.Domain;

import java.util.UUID;

public class DomainDtoToModel {
    public static Domain convert(DomainDto dto, UUID insertUid, UUID updateUid) {
        return new Domain(dto.abbr(), dto.name(), insertUid, updateUid);
    }
}
