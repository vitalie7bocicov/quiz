package ro.uaic.fii.TestService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.TestService.converter.SubjectDtoToModel;
import ro.uaic.fii.TestService.dto.SubjectDto;
import ro.uaic.fii.TestService.model.Subject;
import ro.uaic.fii.TestService.service.SubjectService;
import ro.uaic.fii.TestService.service.TestService;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;
    private final TestService testService;

    public SubjectController(SubjectService subjectService, TestService testService) {
        this.subjectService = subjectService;
        this.testService = testService;
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAll();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Integer id) {
        Subject subject = subjectService.getById(id);
        return ResponseEntity.ok(subject);
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody SubjectDto subjectDto) {
        testService.getById(subjectDto.testId()); // check if test id is valid
        Subject subject = SubjectDtoToModel.convert(subjectDto, subjectDto.userUuid(), null);
        return ResponseEntity.ok(subjectService.save(subject));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Integer id,
                                                 @RequestBody SubjectDto subjectDto) {
        testService.getById(subjectDto.testId());
        Subject subject = SubjectDtoToModel.convert(subjectDto, null, subjectDto.userUuid());
        Subject updatedSubject = subjectService.update(id, subject);
        return ResponseEntity.ok(updatedSubject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable Integer id) {
        subjectService.delete(id);
        return ResponseEntity.ok("Subject with ID: " + id + " deleted.");
    }
}
