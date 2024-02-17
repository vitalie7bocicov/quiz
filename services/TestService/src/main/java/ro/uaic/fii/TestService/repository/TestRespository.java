package ro.uaic.fii.TestService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.TestService.model.Test;

import java.util.Optional;

public interface TestRespository extends JpaRepository<Test, Integer> {
    Optional<Object> findByGroupId(Integer groupId);
}
