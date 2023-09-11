package ro.uaic.fii.UserService.convertor;

import ro.uaic.fii.UserService.dto.InstructorReqDto;
import ro.uaic.fii.UserService.dto.InstructorResDto;
import ro.uaic.fii.UserService.model.Instructor;

public class InstructorConvertor {
    public static Instructor convertReqDto(InstructorReqDto dto)
    {
        return new Instructor(dto.getDomainId(),
                dto.getAccount(),
                dto.getPassword(),
                dto.getName(),
                dto.getEmail(),
                dto.getNotes(),
                dto.getActive(),
                dto.getUserUid(),
                dto.getUserUid());
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
