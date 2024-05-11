package ro.uaic.fii.UserService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.fii.UserService.dto.mapper.StudentMapper;
import ro.uaic.fii.UserService.dto.reqDto.StudentCreateDto;
import ro.uaic.fii.UserService.dto.reqDto.StudentUpdateDto;
import ro.uaic.fii.UserService.dto.resDto.StudentResDto;
import ro.uaic.fii.UserService.exceptions.NotFoundException;
import ro.uaic.fii.UserService.repository.StudentGroupRepo;
import ro.uaic.fii.UserService.repository.StudentRepository;
import ro.uaic.fii.UserService.repository.model.Student;
import ro.uaic.fii.UserService.util.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentGroupRepo studentGroupRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentMapper studentMapper;

    public Student getByAccount(String account) {
        return studentRepository.findByAccount(account)
                .orElseThrow(() -> new NotFoundException("Student", account));
    }

    public StudentResDto getById(UUID id) {
         Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student", id));
        return studentMapper.toDto(student);
    }

    public StudentResDto save(StudentCreateDto createDto) {
        if (!studentGroupRepository.existsById(createDto.getGroupId())) {
            throw new NotFoundException("StudentGroup", createDto.getGroupId());
        }
        createDto.setPassword(passwordEncoder.encode(createDto.getPassword()));
        Student student = studentMapper.dtoToEntity(createDto);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }

    public List<StudentResDto> getAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(studentMapper::toDto).toList();
    }

    public StudentResDto update(UUID id, StudentUpdateDto updateDto) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student", id));

        if (!studentGroupRepository.existsById(updateDto.getGroupId())) {
            throw new NotFoundException("StudentGroup", updateDto.getGroupId());
        }

        existingStudent.setDomainId(updateDto.getDomainId());
        existingStudent.setSessionId(updateDto.getSessionId());
        existingStudent.setGroupId(updateDto.getGroupId());
        existingStudent.setCode(updateDto.getCode());
        existingStudent.setAccount(updateDto.getAccount());
        existingStudent.setName(updateDto.getName());
        existingStudent.setEmail(updateDto.getEmail());
        existingStudent.setNotes(updateDto.getNotes());
        existingStudent.setActive(updateDto.isActive());
        existingStudent.setUpdateUid(updateDto.getUserUid());
        Student updatedStudent = studentRepository.save(existingStudent);
        return studentMapper.toDto(updatedStudent);
    }

    public void deleteById(UUID id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student", id));
        studentRepository.deleteById(id);
    }

    public boolean checkPassword(String plainPassword, String encPassword) {
        return passwordEncoder.matches(plainPassword, encPassword);
    }
}
