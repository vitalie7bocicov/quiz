package ro.uaic.fii.CourseService.CourseService.service;

import org.springframework.stereotype.Service;
import ro.uaic.fii.CourseService.CourseService.exceptions.BadRequestException;
import ro.uaic.fii.CourseService.CourseService.exceptions.NotFoundException;
import ro.uaic.fii.CourseService.CourseService.model.Course;
import ro.uaic.fii.CourseService.CourseService.repository.CourseRepository;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Course save(Course course) {
        try {
            return repository.save(course);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<Course> getAll() {
        return repository.findAll();
    }

    public Course getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course with ID: " + id + " not found."));
    }

    public Course update(int id, Course course) {
        Course existingCourse = getById(id);
        existingCourse.setDomainId(course.getDomainId());
        existingCourse.setSessionId(course.getSessionId());
        existingCourse.setName(course.getName());
        existingCourse.setAbbr(course.getAbbr());
        existingCourse.setNotes(course.getNotes());
        existingCourse.setUpdateUid(course.getUpdateUid());
        return repository.save(existingCourse);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
