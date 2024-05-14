package ro.uaic.fii.CourseService.CourseService.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.uaic.fii.CourseService.CourseService.dto.SectionTopicDto;
import ro.uaic.fii.CourseService.CourseService.dto.reqDto.SectionReqDto;
import ro.uaic.fii.CourseService.CourseService.dto.resDto.SectionResDto;
import ro.uaic.fii.CourseService.CourseService.service.SectionService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sections")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;
    private final HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<SectionResDto>> getAllSections() {
        return ResponseEntity.ok(sectionService.getAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<SectionResDto> getSection(@PathVariable int id) {
        return ResponseEntity.ok(sectionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SectionResDto> createSection(@Valid @RequestBody SectionReqDto dto) {
        SectionResDto savedSection = sectionService.save(dto);
        String uri = getUriString(savedSection);
        return ResponseEntity.created(URI.create(uri)).body(savedSection);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectionResDto> updateSection(@PathVariable Integer id,
                                                 @Valid @RequestBody SectionReqDto dto) {
        return ResponseEntity.ok(sectionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Integer id) {
        sectionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addTopic")
    public ResponseEntity<String> addTopicToCourse(@Valid @RequestBody SectionTopicDto sectionTopicDto) {
        sectionService.addTopicToSection(sectionTopicDto);
        return ResponseEntity.ok("Topic added to the section successfully");
    }
    private String getUriString(SectionResDto dto) {
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUriString();
    }
}
