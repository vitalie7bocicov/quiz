package ro.uaic.fii.TestService.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.TestService.converter.ParticipantDtoToModel;
import ro.uaic.fii.TestService.dto.ParticipantDto;
import ro.uaic.fii.TestService.model.Participant;
import ro.uaic.fii.TestService.service.ParticipantService;
import ro.uaic.fii.TestService.service.TestService;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    private static final Logger logger = Logger.getLogger(ParticipantController.class.getName());
    private HttpServletRequest request;
    private final ParticipantService participantService;
    private final TestService testService;

    public ParticipantController(HttpServletRequest request, ParticipantService participantService, TestService testService) {
        this.participantService = participantService;
        this.testService = testService;
        this.request = request;
    }

    @GetMapping
    public ResponseEntity<List<Participant>> getAllParticipants() {
        List<Participant> participants = participantService.getAll();
        return ResponseEntity.ok(participants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Integer id) {
        Participant participant = participantService.getById(id);
        return ResponseEntity.ok(participant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParticipant(@PathVariable Integer id) {
        participantService.delete(id);
        return ResponseEntity.ok("Participant with ID: " + id + " deleted.");
    }

    @PostMapping
    public ResponseEntity<Participant> addParticipant(@RequestBody ParticipantDto dto) {
        String ipAddress = request.getRemoteAddr();
        String locale = request.getLocale().getLanguage();
        testService.getById(dto.testId());
        Participant participant = ParticipantDtoToModel.convert(dto, ipAddress, locale, dto.userUuid(), null);
        return ResponseEntity.ok(participantService.save(participant));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable Integer id,
                                                         @RequestBody ParticipantDto dto) {
        Participant participant = ParticipantDtoToModel.convert(dto,
                request.getRemoteAddr(),
                request.getLocale().getLanguage(),
                null,
                dto.userUuid());
        testService.getById(dto.testId());
        Participant updatedParticipant = participantService.update(id, participant);
        return ResponseEntity.ok(updatedParticipant);
    }

}
