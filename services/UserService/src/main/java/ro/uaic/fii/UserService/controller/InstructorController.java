package ro.uaic.fii.UserService.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.UserService.convertor.InstructorConvertor;
import ro.uaic.fii.UserService.dto.LoginReq;
import ro.uaic.fii.UserService.dto.InstructorReqDto;
import ro.uaic.fii.UserService.dto.InstructorResDto;
import ro.uaic.fii.UserService.model.Instructor;
import ro.uaic.fii.UserService.service.InstructorService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }
    @PostMapping
    public ResponseEntity<InstructorResDto> addInstructor(@Valid @RequestBody InstructorReqDto instructorDto) {
        Instructor instructor =
                InstructorConvertor.convertReqDto(instructorDto, instructorDto.getUserUid(), null);
        Instructor savedInstructor = instructorService.save(instructor);
        InstructorResDto savedInstructorDto = InstructorConvertor.convertResDto(savedInstructor);
        return ResponseEntity.ok(savedInstructorDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginReq loginDto) {
        Instructor instructor = instructorService.getByAccount(loginDto.getAccount());

        boolean loggedIn =
                instructorService.checkPassword(loginDto.getPassword(), instructor.getPassword());
        if (!loggedIn)
        {
            return ResponseEntity.badRequest().body("Incorrect password.");
        }

        return ResponseEntity.ok("Login successful.");
    }

    @GetMapping
    public ResponseEntity<List<InstructorResDto>> getAll() {
        List<InstructorResDto> instructors = instructorService.getAll().stream()
                .map(InstructorConvertor::convertResDto)
                .toList();
        return ResponseEntity.ok(instructors);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<InstructorResDto> getById(@PathVariable UUID id) {
        Instructor instructor = instructorService.getById(id);
        InstructorResDto instructorDto = InstructorConvertor.convertResDto(instructor);
        return ResponseEntity.ok(instructorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructorResDto> update(@PathVariable UUID id,
                                              @Valid @RequestBody InstructorReqDto instructorDto) {
        Instructor instructor =
                InstructorConvertor.convertReqDto(instructorDto, null, instructorDto.getUserUid());
        Instructor updatedInstructor = instructorService.update(id, instructor);
        InstructorResDto updatedInstructorDto = InstructorConvertor.convertResDto(updatedInstructor);
        return ResponseEntity.ok(updatedInstructorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable UUID id) {
        instructorService.deleteById(id);
        return ResponseEntity.ok("Instructor with ID: " + id + " deleted.");
    }
}
