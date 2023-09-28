package ro.uaic.fii.UserService.convertor;

import ro.uaic.fii.UserService.dto.InstructorReqDto;
import ro.uaic.fii.UserService.dto.InstructorResDto;
import ro.uaic.fii.UserService.model.Instructor;

import java.util.UUID;

public class InstructorConvertor {
    public static Instructor convertReqDto(InstructorReqDto dto, UUID insertUid, UUID updateUid)
    {
        return new Instructor(dto.getDomainId(),
                dto.getAccount(),
                dto.getPassword(),
                dto.getName(),
                dto.getEmail(),
                dto.getNotes(),
                dto.getActive(),
                insertUid,
                updateUid);
    }

    public static InstructorResDto convertResDto(Instructor instructorDto) {
        return new InstructorResDto(instructorDto.getId(),
                instructorDto.getDomainId(),
                instructorDto.getAccount(),
                instructorDto.getName(),
                instructorDto.getEmail(),
                instructorDto.getNotes(),
                instructorDto.getActive());
    }
}
