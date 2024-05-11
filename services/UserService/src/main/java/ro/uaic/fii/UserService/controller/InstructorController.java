package ro.uaic.fii.UserService.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.uaic.fii.UserService.dto.reqDto.InstructorCreateDto;
import ro.uaic.fii.UserService.dto.reqDto.InstructorUpdateDto;
import ro.uaic.fii.UserService.dto.reqDto.LoginReq;
import ro.uaic.fii.UserService.dto.resDto.InstructorResDto;
import ro.uaic.fii.UserService.service.InstructorService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/instructors")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;
    private final HttpServletRequest request;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginReq loginDto) {
        return ResponseEntity.ok("Login successful.");
    }
    @PostMapping
    public ResponseEntity<InstructorResDto> addInstructor(@Valid @RequestBody InstructorCreateDto instructorDto) {
        InstructorResDto savedInstructor = instructorService.save(instructorDto);
        String uri = getUriString(savedInstructor);
        return ResponseEntity.created(URI.create(uri)).body(savedInstructor);
    }

    @GetMapping
    public ResponseEntity<List<InstructorResDto>> getAll() {
        return ResponseEntity.ok(instructorService.getAll());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<InstructorResDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(instructorService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructorResDto> update(@PathVariable UUID id,
                                              @Valid @RequestBody InstructorUpdateDto instructorDto) {
        return ResponseEntity.ok(instructorService.update(id, instructorDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable UUID id) {
        instructorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private String getUriString(InstructorResDto dto) {
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUriString();
    }
}
