package ro.uaic.fii.UserService.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.uaic.fii.UserService.dto.reqDto.LoginReq;
import ro.uaic.fii.UserService.dto.reqDto.StudentCreateDto;
import ro.uaic.fii.UserService.dto.reqDto.StudentUpdateDto;
import ro.uaic.fii.UserService.dto.resDto.StudentResDto;
import ro.uaic.fii.UserService.repository.model.Student;
import ro.uaic.fii.UserService.service.StudentService;

import java.util.List;
import java.util.UUID;

import static java.net.URI.create;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<StudentResDto>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<StudentResDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(studentService.getById(id));
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

    @PostMapping
    public ResponseEntity<StudentResDto> addStudent(@Valid @RequestBody StudentCreateDto dto) {
        StudentResDto savedStudent = studentService.save(dto);
        String uri = getUriString(savedStudent);
        return ResponseEntity.created(create(uri)).body(savedStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResDto> update(@PathVariable UUID id,
                                              @Valid @RequestBody StudentUpdateDto dto) {
        return ResponseEntity.ok(studentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    private String getUriString(StudentResDto dto) {
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUriString();
    }
}
