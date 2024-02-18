package ro.uaic.fii.TestService.service;

import org.springframework.stereotype.Service;
import ro.uaic.fii.TestService.exceptions.BadRequestException;
import ro.uaic.fii.TestService.exceptions.NotFoundException;
import ro.uaic.fii.TestService.model.Test;
import ro.uaic.fii.TestService.repository.TestRespository;

import java.util.List;

@Service
public class TestService {

    private final TestRespository testRespository;

    public TestService(TestRespository testRespository) {
        this.testRespository = testRespository;
    }

    public List<Test> getAll() {
        return testRespository.findAll();
    }

    public Test getById(Integer id) {
        return testRespository.findById(id)
                .orElseThrow(() -> new NotFoundException("Test with ID: " + id + " not found."));
    }

    public Test getByGroupId(Integer groupId) {
        return (Test) testRespository.findByGroupId(groupId)
                .orElseThrow(() -> new NotFoundException("Test with Group ID: " + groupId + " not found."));
    }

    public Test save(Test test) {
        try {
            return testRespository.save(test);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Test update(Integer id, Test test) {
        Test testToUpdate = getById(id);
        testToUpdate.setCourseId(test.getCourseId());
        testToUpdate.setName(test.getName());
        testToUpdate.setGroupId(test.getGroupId());
        testToUpdate.setStartTime(test.getStartTime());
        testToUpdate.setDuration(test.getDuration());
        testToUpdate.setOptNumber(test.getOptNumber());
        testToUpdate.setAccessCode(test.getAccessCode());
        testToUpdate.setActive(test.getActive());
        testToUpdate.setCompleted(test.getCompleted());
        testToUpdate.setDemo(test.getDemo());
        testToUpdate.setNotes(test.getNotes());
        testToUpdate.setUpdateUid(test.getUpdateUid());
        return testRespository.save(testToUpdate);
    }

    public void deleteById(Integer id) {
        testRespository.deleteById(id);
    }
}
