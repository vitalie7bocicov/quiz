package ro.uaic.fii.DomainService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.uaic.fii.DomainService.model.Domain;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Integer> {

}
