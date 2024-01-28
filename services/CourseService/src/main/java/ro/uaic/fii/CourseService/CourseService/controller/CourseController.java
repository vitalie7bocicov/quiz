package ro.uaic.fii.CourseService.CourseService.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.CourseService.CourseService.converter.CourseDtoToModel;
import ro.uaic.fii.CourseService.CourseService.dto.CourseDto;
import ro.uaic.fii.CourseService.CourseService.model.Course;
import ro.uaic.fii.CourseService.CourseService.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@Valid @RequestBody CourseDto dto) {
        Course course = CourseDtoToModel.convert(dto, dto.userUid(), null);
        Course savedCourse = courseService.save(course);
        return ResponseEntity.ok(savedCourse);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = courseService.getAll();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
        Course course = courseService.getById(id);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable int id,
                                               @RequestBody CourseDto dto) {
        Course course = CourseDtoToModel.convert(dto, null, dto.userUid());
        Course updatedCourse = courseService.update(id, course);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        courseService.deleteById(id);
        return ResponseEntity.ok("Course with ID: " + id + " deleted.");
    }
}
