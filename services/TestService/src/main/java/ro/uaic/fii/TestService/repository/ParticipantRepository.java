package ro.uaic.fii.TestService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.TestService.repository.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
}
