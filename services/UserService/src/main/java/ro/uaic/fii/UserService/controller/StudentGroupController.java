package ro.uaic.fii.UserService.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.UserService.convertor.StudentGroupToModel;
import ro.uaic.fii.UserService.dto.StudentGroupDto;
import ro.uaic.fii.UserService.model.StudentGroup;
import ro.uaic.fii.UserService.service.StudentGroupService;

import java.util.List;

@RestController
@RequestMapping("/student-groups")
public class StudentGroupController {
    private final StudentGroupService studentGroupService;

    public StudentGroupController(StudentGroupService studentGroupService)
    {
        this.studentGroupService = studentGroupService;
    }

    @PostMapping
    public ResponseEntity<StudentGroup> addStudentGroup(@Valid @RequestBody StudentGroupDto studentGroupDto)
    {
        StudentGroup parentGroup = null;
        if (studentGroupDto.getParentGroupId() != null)
        {
            parentGroup = studentGroupService.getStudentGroupById(studentGroupDto.getParentGroupId());
        }
        StudentGroup studentGroup = StudentGroupToModel.convert(studentGroupDto);
        studentGroup.setParentGroup(parentGroup);

        StudentGroup savedStudentGroup = studentGroupService.save(studentGroup);

        return ResponseEntity.ok(savedStudentGroup);
    }

    @GetMapping
    public ResponseEntity<List<StudentGroup>> getAllStudentGroups()
    {
        List<StudentGroup> studentGroups = studentGroupService.getAll();
        return ResponseEntity.ok(studentGroups);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<StudentGroup> getStudentGroupById(@PathVariable Integer id)
    {
        StudentGroup studentGroup = studentGroupService.getById(id);
        return ResponseEntity.ok(studentGroup);
    }

    @PutMapping
    public ResponseEntity<StudentGroup> updateStudentGroup(@PathVariable Integer id,
                                                           @Valid @RequestBody StudentGroupDto studentGroupDto)
    {
        StudentGroup parentGroup = null;
        if (studentGroupDto.getParentGroupId() != null)
        {
            parentGroup = studentGroupService.getStudentGroupById(studentGroupDto.getParentGroupId());
        }
        StudentGroup studentGroup = StudentGroupToModel.convert(studentGroupDto);
        studentGroup.setParentGroup(parentGroup);

        StudentGroup updatedStudentGroup = studentGroupService.update(id, studentGroup);

        return ResponseEntity.ok(updatedStudentGroup);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> deleteStudentGroup(@PathVariable Integer id)
    {
        studentGroupService.deleteById(id);
        return ResponseEntity.ok("Student group with ID: " + id + " deleted.");
    }
}
