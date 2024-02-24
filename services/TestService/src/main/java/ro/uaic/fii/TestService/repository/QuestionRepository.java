package ro.uaic.fii.TestService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.TestService.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
