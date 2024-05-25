package ro.uaic.fii.TestService.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.uaic.fii.TestService.dto.reqDto.ParticipantReqDto;
import ro.uaic.fii.TestService.dto.resDto.ParticipantResDto;
import ro.uaic.fii.TestService.service.ParticipantService;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private static final Logger logger = Logger.getLogger(ParticipantController.class.getName());
    private final HttpServletRequest request;
    private final ParticipantService participantService;

    @GetMapping
    public ResponseEntity<List<ParticipantResDto>> getAllParticipants() {
        return ResponseEntity.ok(participantService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantResDto> getParticipantById(@PathVariable int id) {
        return ResponseEntity.ok(participantService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable int id) {
        participantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ParticipantResDto> addParticipant(@Valid @RequestBody ParticipantReqDto dto) {
        dto.setIpAddress(request.getRemoteAddr());
        dto.setLocale(request.getLocale().getLanguage());
        ParticipantResDto participantResDto = participantService.save(dto);
        String uri = getUriString(participantResDto);
        return ResponseEntity.created(URI.create(uri)).body(participantResDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantResDto> updateParticipant(@PathVariable int id,
                                                             @Valid @RequestBody ParticipantReqDto dto) {
        dto.setIpAddress(request.getRemoteAddr());
        dto.setLocale(request.getLocale().getLanguage());
        ParticipantResDto updatedParticipant = participantService.update(id, dto);
        return ResponseEntity.ok(updatedParticipant);
    }

    private String getUriString(ParticipantResDto dto) {
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUriString();
    }
}
