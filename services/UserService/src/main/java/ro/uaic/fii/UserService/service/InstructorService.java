package ro.uaic.fii.UserService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.fii.UserService.dto.mapper.InstructorMapper;
import ro.uaic.fii.UserService.dto.reqDto.InstructorCreateDto;
import ro.uaic.fii.UserService.dto.reqDto.InstructorUpdateDto;
import ro.uaic.fii.UserService.dto.resDto.InstructorResDto;
import ro.uaic.fii.UserService.exceptions.BadRequestException;
import ro.uaic.fii.UserService.exceptions.NotFoundException;
import ro.uaic.fii.UserService.repository.InstructorRepository;
import ro.uaic.fii.UserService.repository.model.Instructor;
import ro.uaic.fii.UserService.util.PasswordEncoder;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final PasswordEncoder passwordEncoder;
    private final InstructorMapper instructorMapper;
    public InstructorResDto getByAccount(String account) {
        Instructor instructor =  instructorRepository.findByAccount(account)
                .orElseThrow(() -> new NotFoundException("Instructor with account: " + account + " not found."));
        return instructorMapper.toDto(instructor);
    }

    public InstructorResDto save(InstructorCreateDto createDto) {
        if (instructorRepository.existsByAccount(createDto.getAccount())) {
            throw new BadRequestException("Instructor", createDto.getAccount());
        }
        Instructor instructor = instructorMapper.dtoToEntity(createDto);
        instructor.setPassword(passwordEncoder.encode(createDto.getPassword()));
        return instructorMapper.toDto(instructorRepository.save(instructor));
    }

    public List<InstructorResDto> getAll() {
        List<Instructor> instructors = instructorRepository.findAll();
        return instructors.stream().map(instructorMapper::toDto).toList();
    }
    public InstructorResDto getById(UUID id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Instructor", id));
        return instructorMapper.toDto(instructor);
    }

    public InstructorResDto update(UUID id, InstructorUpdateDto updateDto) {
        Instructor existingInstructor = instructorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Instructor", id));
        existingInstructor.setDomainId(updateDto.getDomainId());
        existingInstructor.setAccount(updateDto.getAccount());
        existingInstructor.setName(updateDto.getName());
        existingInstructor.setEmail(updateDto.getEmail());
        existingInstructor.setNotes(updateDto.getNotes());
        existingInstructor.setActive(updateDto.isActive());
        existingInstructor.setUpdateUid(updateDto.getUserUid());
        Instructor updatedInstructor =  instructorRepository.save(existingInstructor);
        return instructorMapper.toDto(updatedInstructor);
    }

    public void deleteById(UUID id) {
        if (!instructorRepository.existsById(id)) {
            throw new NotFoundException("Instructor", id);
        }
        instructorRepository.deleteById(id);
    }

    public boolean checkPassword(String plainPassword, String encPassword) {
        return passwordEncoder.matches(plainPassword, encPassword);
    }
}
