package ro.uaic.fii.UserService.convertor;

import ro.uaic.fii.UserService.dto.StudentGroupDto;
import ro.uaic.fii.UserService.model.StudentGroup;

public class StudentGroupToModel {

    public static StudentGroup convert(StudentGroupDto dto)
    {
        return new StudentGroup(dto.getDomainId(),
                dto.getSessionId(),
                null,
                dto.getAbbr(),
                dto.getName(),
                dto.getNotes(),
                dto.getUserUid(),
                dto.getUserUid());
    }
}
