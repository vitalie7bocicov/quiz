package ro.uaic.fii.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.UserService.model.Student;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
}
