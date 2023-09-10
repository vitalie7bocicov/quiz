package ro.uaic.fii.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.UserService.model.StudentGroup;

import java.util.UUID;

public interface StudentGroupRepo extends JpaRepository<StudentGroup, Integer> {
}
