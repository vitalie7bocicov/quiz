package ro.uaic.fii.CourseService.CourseService.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.CourseService.CourseService.converter.SectionDtoToModel;
import ro.uaic.fii.CourseService.CourseService.dto.SectionDto;
import ro.uaic.fii.CourseService.CourseService.model.Section;
import ro.uaic.fii.CourseService.CourseService.service.CourseService;
import ro.uaic.fii.CourseService.CourseService.service.SectionService;

import java.util.List;

@RestController
@RequestMapping("/sections")
public class SectionController {

    private final SectionService sectionService;
    private final CourseService courseService;

    public SectionController(SectionService sectionService, CourseService courseService) {
        this.sectionService = sectionService;
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Section>> getAllSections() {
        List<Section> sections = sectionService.getAll();
        return ResponseEntity.ok(sections);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Section> getSection(@PathVariable Integer id) {
        Section section = sectionService.getById(id);
        return ResponseEntity.ok(section);
    }

    @PostMapping
    public ResponseEntity<Section> createSection(@Valid @RequestBody SectionDto dto) {
        Section section = SectionDtoToModel.convert(dto, dto.userUid(), null);
        courseService.getById(dto.courseId()); // throws not found ex
        Section savedSection = sectionService.save(section);
        return ResponseEntity.ok(savedSection);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Section> updateSection(@PathVariable Integer id,
                                                 @Valid @RequestBody SectionDto dto) {
        Section section = SectionDtoToModel.convert(dto, null , dto.userUid());
        courseService.getById(dto.courseId()); // throws not found ex
        Section updatedSection = sectionService.update(id, section);
        return ResponseEntity.ok(updatedSection);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSection(@PathVariable Integer id) {
        sectionService.deleteById(id);
        return ResponseEntity.ok("Topic with ID: " + id + " deleted.");
    }
}
