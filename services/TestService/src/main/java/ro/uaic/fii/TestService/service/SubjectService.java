package ro.uaic.fii.TestService.service;

import org.springframework.stereotype.Service;
import ro.uaic.fii.TestService.exceptions.BadRequestException;
import ro.uaic.fii.TestService.exceptions.NotFoundException;
import ro.uaic.fii.TestService.model.Subject;
import ro.uaic.fii.TestService.model.Test;
import ro.uaic.fii.TestService.repository.SubjectRepository;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }


    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    public Subject getById(Integer id) {
        return  subjectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subject with id : " + id + " not found."));
    }

    public Subject save(Subject subject) {
        try {
            return subjectRepository.save(subject);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Subject update(Integer id, Subject subject) {
        Subject subjectToUpdate = getById(id);
        subjectToUpdate.setTestId(subject.getTestId());
        subjectToUpdate.setSectionId(subject.getSectionId());
        subjectToUpdate.setTopicId(subjectToUpdate.getTopicId());
        subjectToUpdate.setQsType(subject.getQsType());
        subjectToUpdate.setQsNumber(subjectToUpdate.getQsNumber());
        subjectToUpdate.setOrderNumber(subject.getOrderNumber());
        subjectToUpdate.setUpdateUid(subject.getUpdateUid());
        return subjectRepository.save(subjectToUpdate);
    }

    public void delete(Integer id) {
        subjectRepository.deleteById(id);
    }
}
