package ro.uaic.fii.CourseService.CourseService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.CourseService.CourseService.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
}