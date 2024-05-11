package ro.uaic.fii.UserService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.fii.UserService.dto.mapper.StudentGroupMapper;
import ro.uaic.fii.UserService.dto.reqDto.StudentGroupCreateDto;
import ro.uaic.fii.UserService.dto.reqDto.StudentGroupUpdateDto;
import ro.uaic.fii.UserService.dto.resDto.StudentGroupResDto;
import ro.uaic.fii.UserService.exceptions.BadRequestException;
import ro.uaic.fii.UserService.exceptions.NotFoundException;
import ro.uaic.fii.UserService.repository.StudentGroupRepo;
import ro.uaic.fii.UserService.repository.model.StudentGroup;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentGroupService {
    private final StudentGroupRepo studentGroupRepository;
    private final StudentGroupMapper studentGroupMapper;

    public StudentGroupResDto save(StudentGroupCreateDto createDto) {
        if (studentGroupRepository.existsById(createDto.getDomainId())) {
            throw new BadRequestException("Domain", createDto.getDomainId());
        }
        StudentGroup savedStudentGroup = studentGroupRepository.save(studentGroupMapper.dtoToEntity(createDto));
        return studentGroupMapper.toDto(savedStudentGroup);
    }

    public List<StudentGroupResDto> getAll() {
        List<StudentGroup> studentGroups = studentGroupRepository.findAll();
        return studentGroups.stream().map(studentGroupMapper::toDto).toList();
    }

    public StudentGroupResDto getById(int id) {
        StudentGroup studentGroup = studentGroupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student group", id));
        return studentGroupMapper.toDto(studentGroup);
    }

    public StudentGroupResDto update(int id, StudentGroupUpdateDto updateDto) {
        StudentGroup existingStudentGroup = studentGroupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student group", id));
        existingStudentGroup.setDomainId(updateDto.getDomainId());
        existingStudentGroup.setSessionId(updateDto.getSessionId());
        existingStudentGroup.setParentId(updateDto.getParentId());
        existingStudentGroup.setAbbr(updateDto.getAbbr());
        existingStudentGroup.setName(updateDto.getName());
        existingStudentGroup.setNotes(updateDto.getNotes());
        existingStudentGroup.setUpdateUid(updateDto.getUserUid());
        StudentGroup updatedStudentGroup = studentGroupRepository.save(existingStudentGroup);
        return studentGroupMapper.toDto(updatedStudentGroup);
    }

    public void deleteById(int id) {
        StudentGroup studentGroup = studentGroupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student group", id));
        studentGroupRepository.delete(studentGroup);
    }

    public StudentGroupResDto getStudentGroupById(int studentGroupId) {
        StudentGroup studentGroup = studentGroupRepository.findById(studentGroupId)
                .orElseThrow(() -> new NotFoundException("StudentGroup", studentGroupId));
        return studentGroupMapper.toDto(studentGroup);
    }
}
