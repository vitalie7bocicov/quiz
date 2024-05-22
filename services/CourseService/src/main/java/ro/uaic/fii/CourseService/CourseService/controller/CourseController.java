package ro.uaic.fii.CourseService.CourseService.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.uaic.fii.CourseService.CourseService.dto.CourseTopicDto;
import ro.uaic.fii.CourseService.CourseService.dto.CourseInstructorDto;
import ro.uaic.fii.CourseService.CourseService.dto.CourseStudentGroupDto;
import ro.uaic.fii.CourseService.CourseService.dto.reqDto.CourseReqDto;
import ro.uaic.fii.CourseService.CourseService.dto.resDto.CourseResDto;
import ro.uaic.fii.CourseService.CourseService.service.CourseService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final HttpServletRequest request;
    @GetMapping
    public ResponseEntity<List<CourseResDto>> getCourses() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResDto> getCourseById(@PathVariable int id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CourseResDto> addCourse(@Valid @RequestBody CourseReqDto dto) {
        CourseResDto savedCourse = courseService.save(dto);
        String uri = getUriString(savedCourse);
        return ResponseEntity.created(URI.create(uri)).body(savedCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResDto> updateCourse(@PathVariable int id,
                                               @RequestBody CourseReqDto dto) {
        return ResponseEntity.ok(courseService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addTopic")
    public ResponseEntity<String> addTopicToCourse(@Valid @RequestBody CourseTopicDto courseTopicDto) {
        courseService.addTopicToCourse(courseTopicDto);
        return ResponseEntity.ok("Topic added to the course successfully");
    }

    @PostMapping("/addInstructor")
    public ResponseEntity<String> addInstructorToCourse(@Valid @RequestBody CourseInstructorDto courseInstructorDto) {
        courseService.addInstructorToCourse(courseInstructorDto);
        return ResponseEntity.ok("Instructor added to the course successfully");
    }

    @PostMapping("/addStudentGroup")
    public ResponseEntity<String> addStudentGroupToCourse(@Valid @RequestBody CourseStudentGroupDto courseGroupDto) {
        courseService.addStudentGroupToCourse(courseGroupDto);
        return ResponseEntity.ok("Student group added to the course successfully");
    }
    private String getUriString(CourseResDto dto) {
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUriString();
    }
}
