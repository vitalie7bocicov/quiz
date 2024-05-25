package ro.uaic.fii.TestService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.fii.TestService.dto.mapper.TestMapper;
import ro.uaic.fii.TestService.dto.reqDto.TestReqDto;
import ro.uaic.fii.TestService.dto.resDto.TestResDto;
import ro.uaic.fii.TestService.exceptions.NotFoundException;
import ro.uaic.fii.TestService.repository.model.Test;
import ro.uaic.fii.TestService.repository.TestRespository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRespository testRespository;
    private final TestMapper testMapper;

    public List<TestResDto> getAll() {
        List<Test> tests = testRespository.findAll();
        return tests.stream().map(testMapper::toDto).toList();
    }

    public TestResDto getById(int id) {
        Test test =  testRespository.findById(id)
                .orElseThrow(() -> new NotFoundException("Test", id));
        return testMapper.toDto(test);
    }

    public TestResDto getByGroupId(int groupId) {
        Test test =  testRespository.findByGroupId(groupId)
                .orElseThrow(() -> new NotFoundException("Group id", groupId));
        return testMapper.toDto(test);
    }

    public TestResDto save(TestReqDto dto) {
        Test savedTest = testRespository.save(testMapper.dtoToEntity(dto));
        return testMapper.toDto(savedTest);
    }

    public TestResDto update(int id, TestReqDto dto) {
        Test testToUpdate = testRespository.findById(id)
                .orElseThrow(() -> new NotFoundException("Test", id));
        testToUpdate.setCourseId(dto.getCourseId());
        testToUpdate.setName(dto.getName());
        testToUpdate.setGroupId(dto.getGroupId());
        testToUpdate.setStartTime(dto.getStartTime());
        testToUpdate.setDuration(dto.getDuration());
        testToUpdate.setOptNumber(dto.getOptNumber());
        testToUpdate.setAccessCode(dto.getAccessCode());
        testToUpdate.setActive(dto.isActive());
        testToUpdate.setCompleted(dto.isCompleted());
        testToUpdate.setDemo(dto.isDemo());
        testToUpdate.setNotes(dto.getNotes());
        testToUpdate.setUpdateUid(dto.getUserUid());
        Test updatedTest = testRespository.save(testToUpdate);
        return testMapper.toDto(updatedTest);
    }

    public void deleteById(int id) {
        if (!testRespository.existsById(id)) {
            throw new NotFoundException("Test", id);
        }
        testRespository.deleteById(id);
    }
}
