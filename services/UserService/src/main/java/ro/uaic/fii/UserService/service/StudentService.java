package ro.uaic.fii.UserService.service;

import org.springframework.stereotype.Service;
import ro.uaic.fii.UserService.exceptions.BadRequestException;
import ro.uaic.fii.UserService.exceptions.NotFoundException;
import ro.uaic.fii.UserService.model.Student;
import ro.uaic.fii.UserService.repository.StudentRepository;
import ro.uaic.fii.UserService.util.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final PasswordEncoder passwordEncoder;
    public StudentService(StudentRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Student getByAccount(String account)
    {
        return repository.findByAccount(account)
                .orElseThrow(() -> new NotFoundException("Student with account: " + account + " not found."));
    }

    public Student save(Student student)
    {
        try {
            getById(student.getInsertUid());
            student.setPassword(passwordEncoder.encode(student.getPassword()));
            return repository.save(student);
        }
        catch (NotFoundException e)
        {
            throw new BadRequestException("Student with ID: " + student.getInsertUid() + " not found.");
        }
        catch (Exception e)
        {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with ID: " + id + " not found."));
    }

    public Student update(UUID id, Student updatedStudent) throws NotFoundException {
        Student existingStudent = getById(id);
        existingStudent.setDomainId(updatedStudent.getDomainId());
        existingStudent.setSessionId(updatedStudent.getSessionId());
        existingStudent.setGroup(updatedStudent.getGroup());
        existingStudent.setCode(updatedStudent.getCode());
        existingStudent.setAccount(updatedStudent.getAccount());
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setNotes(updatedStudent.getNotes());
        existingStudent.setActive(updatedStudent.getActive());
        return repository.save(existingStudent);
    }

    public void deleteById(UUID id) {
        Student student = getById(id);
        repository.delete(student);
    }

    public boolean checkPassword(String plainPassword, String encPassword) {
        return passwordEncoder.matches(plainPassword, encPassword);
    }
}
