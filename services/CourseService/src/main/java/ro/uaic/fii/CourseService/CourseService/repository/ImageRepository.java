package ro.uaic.fii.CourseService.CourseService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.CourseService.CourseService.repository.model.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
