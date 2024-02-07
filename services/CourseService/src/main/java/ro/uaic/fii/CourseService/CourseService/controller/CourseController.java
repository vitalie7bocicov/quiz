package ro.uaic.fii.CourseService.CourseService.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.CourseService.CourseService.converter.CourseDtoToModel;
import ro.uaic.fii.CourseService.CourseService.dto.CourseDto;
import ro.uaic.fii.CourseService.CourseService.dto.CourseTopicDto;
import ro.uaic.fii.CourseService.CourseService.dto.CourseInstructorDto;
import ro.uaic.fii.CourseService.CourseService.dto.CourseStudentGroupDto;
import ro.uaic.fii.CourseService.CourseService.model.Course;
import ro.uaic.fii.CourseService.CourseService.model.Topic;
import ro.uaic.fii.CourseService.CourseService.service.CourseService;
import ro.uaic.fii.CourseService.CourseService.service.TopicService;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TopicService topicService;

    public CourseController(CourseService courseService, TopicService topicService) {
        this.courseService = courseService;
        this.topicService = topicService;
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

    @PostMapping("/addTopic")
    public ResponseEntity<String> addTopicToCourse(@Valid @RequestBody CourseTopicDto courseTopicDto) {
        Course course = courseService.getById(courseTopicDto.courseId());
        Topic topic = topicService.getById(courseTopicDto.topicId());
        course.getTopics().add(topic);
        courseService.save(course);
        return ResponseEntity.ok("Topic added to the course successfully");
    }

    @PostMapping("/addInstructor")
    public ResponseEntity<String> addInstructorToCourse(@Valid @RequestBody CourseInstructorDto courseInstructorDto) {
        Course course = courseService.getById(courseInstructorDto.courseId());
        course.getInstructorIds().add(courseInstructorDto.instructorId());
        courseService.save(course);
        return ResponseEntity.ok("Instructor added to the course successfully");
    }

    @PostMapping("/{courseId}/addStudentGroup")
    public ResponseEntity<String> addStudentGroupToCourse(@Valid @RequestBody CourseStudentGroupDto courseGroupDto) {
        Course course = courseService.getById(courseGroupDto.courseId());
        course.getStudentGroupIds().add(courseGroupDto.studentGroupId());
        courseService.save(course);
        return ResponseEntity.ok("Student group added to the course successfully");
    }
}
