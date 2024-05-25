package ro.uaic.fii.TestService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.TestService.repository.model.Response;

public interface ResponseRepository extends JpaRepository<Response, Integer> {
}
