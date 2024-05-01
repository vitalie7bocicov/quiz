package ro.uaic.fii.CollegeMgmtSvc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.SessionCreateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.SessionUpdateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.resDto.SessionDto;
import ro.uaic.fii.CollegeMgmtSvc.service.SessionService;

import java.util.List;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionDto> addSession(@Valid @RequestBody SessionCreateDto dto) {
        SessionDto savedSession = sessionService.save(dto);
        return ResponseEntity.ok(savedSession);
    }

    @GetMapping
    public ResponseEntity<List<SessionDto>> getSessions() {
        return ResponseEntity.ok(sessionService.getAll());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<SessionDto> getById(@PathVariable int id) {
        return ResponseEntity.ok(sessionService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionDto> updateSession(@PathVariable int id,
                                                @RequestBody SessionUpdateDto dto) {
        return ResponseEntity.ok(sessionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSession(@PathVariable int id) {
        sessionService.deleteById(id);
        return ResponseEntity.ok("Session with ID: " + id + " deleted.");
    }
}
