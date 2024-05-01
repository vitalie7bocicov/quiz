package ro.uaic.fii.CollegeMgmtSvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.uaic.fii.CollegeMgmtSvc.repository.model.Domain;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Integer> {

}
