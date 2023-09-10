package ro.uaic.fii.UserService.service;

import org.springframework.stereotype.Service;
import ro.uaic.fii.UserService.exceptions.BadRequestException;
import ro.uaic.fii.UserService.exceptions.NotFoundException;
import ro.uaic.fii.UserService.model.StudentGroup;
import ro.uaic.fii.UserService.repository.StudentGroupRepo;

import java.util.List;

@Service
public class StudentGroupService {
    private final StudentGroupRepo repository;
    public StudentGroupService(StudentGroupRepo repository) {
        this.repository = repository;
    }

    public StudentGroup save(StudentGroup studentGroup)
    {
        try {
            return repository.save(studentGroup);
        }
        catch (Exception e)
        {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<StudentGroup> getAll() {
        return repository.findAll();
    }

    public StudentGroup getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student group with ID: " + id + " not found."));
    }

    public StudentGroup update(int id, StudentGroup updatedStudentGroup) throws NotFoundException {
        StudentGroup existingStudentGroup = getById(id);
        existingStudentGroup.setDomainId(updatedStudentGroup.getDomainId());
        existingStudentGroup.setSessionId(updatedStudentGroup.getSessionId());
        existingStudentGroup.setParentGroup(updatedStudentGroup.getParentGroup());
        existingStudentGroup.setAbbr(updatedStudentGroup.getAbbr());
        existingStudentGroup.setName(updatedStudentGroup.getName());
        existingStudentGroup.setNotes(updatedStudentGroup.getNotes());
        return repository.save(existingStudentGroup);
    }

    public void deleteById(int id) {
        StudentGroup studentGroup = getById(id);
        repository.delete(studentGroup);
    }

    public StudentGroup getStudentGroupById(Integer studentGroupId) {
        return repository.findById(studentGroupId)
                .orElseThrow(() -> new NotFoundException("Student group with ID: " + studentGroupId + " not found."));
    }
}
