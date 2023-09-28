package ro.uaic.fii.UserService.convertor;

import ro.uaic.fii.UserService.dto.StudentGroupDto;
import ro.uaic.fii.UserService.model.StudentGroup;

import java.util.UUID;

public class StudentGroupToModel {

    public static StudentGroup convert(StudentGroupDto dto, UUID insertUid, UUID updateUid)
    {
        return new StudentGroup(dto.getDomainId(),
                dto.getSessionId(),
                null,
                dto.getAbbr(),
                dto.getName(),
                dto.getNotes(),
                insertUid,
                updateUid);
    }
}
