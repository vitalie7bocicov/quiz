package ro.uaic.fii.CollegeMgmtSvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.SessionCreateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.SessionUpdateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.resDto.SessionDto;
import ro.uaic.fii.CollegeMgmtSvc.service.SessionService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    private final HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<SessionDto>> getSessions() {
        return ResponseEntity.ok(sessionService.getAll());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<SessionDto> getById(@PathVariable int id) {
        return ResponseEntity.ok(sessionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SessionDto> addSession(@Valid @RequestBody SessionCreateDto dto) {
        SessionDto savedSession = sessionService.save(dto);
        String uri = getUriString(savedSession);
        return ResponseEntity.created(URI.create(uri)).body(savedSession);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionDto> updateSession(@PathVariable int id,
                                                @RequestBody SessionUpdateDto dto) {
        return ResponseEntity.ok(sessionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable int id) {
        sessionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private String getUriString(SessionDto sessionDto) {
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(sessionDto.getId())
                .toUriString();
    }
}
