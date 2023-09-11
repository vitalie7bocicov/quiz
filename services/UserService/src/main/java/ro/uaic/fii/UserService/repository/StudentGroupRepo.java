package ro.uaic.fii.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.UserService.model.StudentGroup;

public interface StudentGroupRepo extends JpaRepository<StudentGroup, Integer> {
}
