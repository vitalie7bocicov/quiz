package ro.uaic.fii.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.UserService.repository.model.Instructor;

import java.util.Optional;
import java.util.UUID;

public interface InstructorRepository extends JpaRepository<Instructor, UUID> {

     Optional<Instructor> findByAccount(String account);

    boolean existsByAccount(String account);
}
