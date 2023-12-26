package ro.uaic.fii.UserService.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.UserService.convertor.StudentConvertor;
import ro.uaic.fii.UserService.dto.LoginReq;
import ro.uaic.fii.UserService.dto.StudentReqDto;
import ro.uaic.fii.UserService.dto.StudentResDto;
import ro.uaic.fii.UserService.model.Student;
import ro.uaic.fii.UserService.model.StudentGroup;
import ro.uaic.fii.UserService.service.StudentGroupService;
import ro.uaic.fii.UserService.service.StudentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final StudentGroupService groupService;

    public StudentController(StudentService studentService, StudentGroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }
    @PostMapping
    public ResponseEntity<StudentResDto> addStudent(@Valid @RequestBody StudentReqDto dto) {
        StudentGroup group = groupService.getById(dto.getGroupId());
        Student student =
                StudentConvertor.convertReqDto(dto, group, dto.getUserUid(), null);
        Student savedStudent = studentService.save(student);
        StudentResDto savedStudentDto = StudentConvertor.convertResDto(savedStudent);
        return ResponseEntity.ok(savedStudentDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginReq loginReq) {
        Student student = studentService.getByAccount(loginReq.getAccount());

        boolean loggedIn =
                studentService.checkPassword(loginReq.getPassword(), student.getPassword());

        if (!loggedIn)
        {
            return ResponseEntity.badRequest().body("Incorrect password.");
        }

        return ResponseEntity.ok("Login successful.");
    }

    @GetMapping
    public ResponseEntity<List<StudentResDto>> getAll() {
        List<StudentResDto> students = studentService.getAll().stream()
                .map(StudentConvertor::convertResDto)
                .toList();
        return ResponseEntity.ok(students);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<StudentResDto> getById(@PathVariable UUID id) {
        Student student = studentService.getById(id);
        StudentResDto studentDto = StudentConvertor.convertResDto(student);
        return ResponseEntity.ok(studentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResDto> update(@PathVariable UUID id,
                                              @Valid @RequestBody StudentReqDto dto) {
        StudentGroup group = groupService.getById(dto.getGroupId());
        Student student =
                StudentConvertor.convertReqDto(dto, group, null, dto.getUserUid());
        Student updatedStudent = studentService.update(id, student);
        StudentResDto updatedStudentDto = StudentConvertor.convertResDto(updatedStudent);
        return ResponseEntity.ok(updatedStudentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable UUID id) {
        studentService.deleteById(id);
        return ResponseEntity.ok("Student with ID: " + id + " deleted.");
    }
}
