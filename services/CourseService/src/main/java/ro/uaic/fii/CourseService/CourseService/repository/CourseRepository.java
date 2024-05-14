package ro.uaic.fii.CourseService.CourseService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.CourseService.CourseService.repository.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
