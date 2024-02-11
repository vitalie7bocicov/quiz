package ro.uaic.fii.TestService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.TestService.model.Test;

public interface TestRespository extends JpaRepository<Test, Integer> {
}
