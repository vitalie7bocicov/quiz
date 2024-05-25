package ro.uaic.fii.TestService.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.TestService.dto.reqDto.SubjectReqDto;
import ro.uaic.fii.TestService.dto.resDto.SubjectResDto;
import ro.uaic.fii.TestService.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SubjectResDto>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResDto> getSubjectById(@PathVariable int id) {
        return ResponseEntity.ok(subjectService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SubjectResDto> createSubject(@Valid @RequestBody SubjectReqDto subjectDto) {
        return ResponseEntity.ok(subjectService.save(subjectDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectResDto> updateSubject(@PathVariable int id,
                                                 @Valid @RequestBody SubjectReqDto subjectDto) {
        return ResponseEntity.ok(subjectService.update(id, subjectDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable int id) {
        subjectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
