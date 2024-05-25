package ro.uaic.fii.TestService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.TestService.repository.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
