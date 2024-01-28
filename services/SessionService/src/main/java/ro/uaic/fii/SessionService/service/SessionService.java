package ro.uaic.fii.SessionService.service;

import org.springframework.stereotype.Service;
import ro.uaic.fii.SessionService.exceptions.BadRequestException;
import ro.uaic.fii.SessionService.exceptions.NotFoundException;
import ro.uaic.fii.SessionService.model.Session;
import ro.uaic.fii.SessionService.repository.SessionRepository;

import java.util.List;

@Service
public class SessionService {
    private final SessionRepository repository;
    public SessionService(SessionRepository repository) {
        this.repository = repository;
    }

    public Session save(Session session) {
        try {
            return repository.save(session);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<Session> getAll() {
        return repository.findAll();
    }

    public Session getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Session with ID: " + id + " not found."));
    }

    public Session update(int id, Session updateSession) throws NotFoundException {
        Session existingSession = getById(id);
        existingSession.setDomainId(updateSession.getDomainId());
        existingSession.setName(updateSession.getName());
        existingSession.setActive(updateSession.getActive());
        existingSession.setNotes(updateSession.getNotes());
        existingSession.setUpdateUid(updateSession.getUpdateUid());
        return repository.save(existingSession);
    }

    public void deleteDomainById(int id) {
        Session session = getById(id);
        repository.delete(session);
    }
}
