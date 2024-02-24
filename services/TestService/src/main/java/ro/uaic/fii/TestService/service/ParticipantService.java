package ro.uaic.fii.TestService.service;

import org.springframework.stereotype.Service;
import ro.uaic.fii.TestService.exceptions.NotFoundException;
import ro.uaic.fii.TestService.model.Participant;
import ro.uaic.fii.TestService.repository.ParticipantRepository;

import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public void delete(Integer id) {
        participantRepository.deleteById(id);
    }

    public Participant getById(Integer id) {
        return  participantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Participant with id : " + id + " not found."));
    }

    public Participant save(Participant participant) {
        return participantRepository.save(participant);
    }

    public List<Participant> getAll() {
        return participantRepository.findAll();
    }

    public Participant update(Integer id, Participant participant) {
        Participant participantToUpdate = getById(id);
        participantToUpdate.setTestId(participant.getTestId());
        participantToUpdate.setIpAddress(participant.getIpAddress());
        participantToUpdate.setLocale(participant.getLocale());
        participantToUpdate.setPoints(participant.getPoints());
        participantToUpdate.setActive(participant.isActive());
        participantToUpdate.setUpdateUid(participant.getUpdateUid());
        return save(participantToUpdate);
    }
}
