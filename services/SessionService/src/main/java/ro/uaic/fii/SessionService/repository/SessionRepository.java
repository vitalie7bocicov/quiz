package ro.uaic.fii.SessionService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.uaic.fii.SessionService.model.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

}
