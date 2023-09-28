package ro.uaic.fii.UserService.convertor;

import ro.uaic.fii.UserService.dto.StudentReqDto;
import ro.uaic.fii.UserService.dto.StudentResDto;
import ro.uaic.fii.UserService.model.Student;
import ro.uaic.fii.UserService.model.StudentGroup;

import java.util.UUID;

public class StudentConvertor {
    public static Student convertReqDto(StudentReqDto dto, StudentGroup group, UUID insertUid, UUID updateUid) {
        return new Student(dto.getDomainId(),
                dto.getSessionId(),
                group,
                dto.getCode(),
                dto.getAccount(),
                dto.getPassword(),
                dto.getName(),
                dto.getEmail(),
                dto.getNotes(),
                dto.getActive(),
                insertUid,
                updateUid);
    }

    public static StudentResDto convertResDto(Student studentDto) {
        return new StudentResDto(studentDto.getDomainId(),
                studentDto.getSessionId(),
                studentDto.getGroup().getId(),
                studentDto.getCode(),
                studentDto.getAccount(),
                studentDto.getName(),
                studentDto.getEmail(),
                studentDto.getNotes(),
                studentDto.getActive());
    }
}
