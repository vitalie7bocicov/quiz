package ro.uaic.fii.UserService.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.uaic.fii.UserService.dto.reqDto.StudentGroupCreateDto;
import ro.uaic.fii.UserService.dto.reqDto.StudentGroupUpdateDto;
import ro.uaic.fii.UserService.dto.resDto.StudentGroupResDto;
import ro.uaic.fii.UserService.service.StudentGroupService;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/student-groups")
@RequiredArgsConstructor
public class StudentGroupController {

    private final StudentGroupService studentGroupService;
    private final HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<StudentGroupResDto>> getAllStudentGroups() {
        return ResponseEntity.ok(studentGroupService.getAll());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<StudentGroupResDto> getStudentGroupById(@PathVariable int id) {
        return ResponseEntity.ok(studentGroupService.getById(id));
    }

    @PostMapping
    public ResponseEntity<StudentGroupResDto> addStudentGroup(@Valid @RequestBody StudentGroupCreateDto studentGroupDto) {
        StudentGroupResDto savedStudentGroup = studentGroupService.save(studentGroupDto);
        String uri = getUriString(savedStudentGroup);
        return ResponseEntity.created(URI.create(uri)).body(savedStudentGroup);
    }
    @PutMapping({"/{id}"})
    public ResponseEntity<StudentGroupResDto> updateStudentGroup(@PathVariable int id,
                                                           @Valid @RequestBody StudentGroupUpdateDto updateDto) {
        return ResponseEntity.ok(studentGroupService.update(id, updateDto));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteStudentGroup(@PathVariable int id) {
        studentGroupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private String getUriString(StudentGroupResDto dto) {
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUriString();
    }
}
