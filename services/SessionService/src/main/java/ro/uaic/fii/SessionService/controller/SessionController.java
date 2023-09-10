package ro.uaic.fii.SessionService.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.SessionService.convertor.SessionDtoToModel;
import ro.uaic.fii.SessionService.dto.SessionDto;
import ro.uaic.fii.SessionService.model.Session;
import ro.uaic.fii.SessionService.service.SessionService;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }
    @PostMapping
    public ResponseEntity<Session> addSession(@Valid @RequestBody SessionDto sessionDto)
    {
        Session session = SessionDtoToModel.convert(sessionDto);
        Session savedSession = sessionService.save(session);
        return ResponseEntity.ok(savedSession);
    }

    @GetMapping
    public ResponseEntity<List<Session>> getSessions()
    {
        List<Session> sessions = sessionService.getAll();
        return ResponseEntity.ok(sessions);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Session> getById(@PathVariable int id)
    {
        Session session = sessionService.getById(id);
        return ResponseEntity.ok(session);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSession(@PathVariable int id,
                                                @RequestBody SessionDto sessionDto)
    {
        Session updateSession = SessionDtoToModel.convert(sessionDto);
        Session session = sessionService.update(id, updateSession);
        return ResponseEntity.ok(session);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSession(@PathVariable int id)
    {
        sessionService.deleteDomainById(id);
        return ResponseEntity.ok("Session with ID: " + id + " deleted.");
    }
}
