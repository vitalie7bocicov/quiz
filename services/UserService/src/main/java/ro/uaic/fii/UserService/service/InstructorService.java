package ro.uaic.fii.UserService.service;

import org.springframework.stereotype.Service;
import ro.uaic.fii.UserService.exceptions.BadRequestException;
import ro.uaic.fii.UserService.exceptions.NotFoundException;
import ro.uaic.fii.UserService.model.Instructor;
import ro.uaic.fii.UserService.repository.InstructorRepository;

import java.util.List;
import java.util.UUID;

@Service
public class InstructorService {
    private final InstructorRepository repository;
    public InstructorService(InstructorRepository repository) {
        this.repository = repository;
    }

    public Instructor save(Instructor instructor)
    {
        try {
            return repository.save(instructor);
        }
        catch (Exception e)
        {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<Instructor> getAll() {
        return repository.findAll();
    }

    public Instructor getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Instructor with ID: " + id + " not found."));
    }

    public Instructor update(UUID id, Instructor updatedInstructor) throws NotFoundException {
        Instructor existingInstructor = getById(id);
        existingInstructor.setDomainId(updatedInstructor.getDomainId());
        existingInstructor.setAccount(updatedInstructor.getAccount());
        existingInstructor.setName(updatedInstructor.getName());
        existingInstructor.setEmail(updatedInstructor.getEmail());
        existingInstructor.setNotes(updatedInstructor.getNotes());
        existingInstructor.setActive(updatedInstructor.getActive());
        return repository.save(existingInstructor);
    }

    public void deleteById(UUID id) {
        Instructor instructor = getById(id);
        repository.delete(instructor);
    }
}
