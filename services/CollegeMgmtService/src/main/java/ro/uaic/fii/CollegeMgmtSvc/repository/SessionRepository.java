package ro.uaic.fii.CollegeMgmtSvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.uaic.fii.CollegeMgmtSvc.repository.model.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

}
