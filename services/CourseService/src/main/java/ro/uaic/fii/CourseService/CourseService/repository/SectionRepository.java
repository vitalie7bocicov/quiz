package ro.uaic.fii.CourseService.CourseService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.CourseService.CourseService.model.Section;

public interface SectionRepository extends JpaRepository<Section, Integer> {
}
