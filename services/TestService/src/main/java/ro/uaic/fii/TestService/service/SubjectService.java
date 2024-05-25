package ro.uaic.fii.TestService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.fii.TestService.dto.mapper.SubjectMapper;
import ro.uaic.fii.TestService.dto.reqDto.SubjectReqDto;
import ro.uaic.fii.TestService.dto.resDto.SubjectResDto;
import ro.uaic.fii.TestService.exceptions.BadRequestException;
import ro.uaic.fii.TestService.exceptions.NotFoundException;
import ro.uaic.fii.TestService.repository.model.Subject;
import ro.uaic.fii.TestService.repository.SubjectRepository;
import ro.uaic.fii.TestService.repository.TestRespository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final TestRespository testRespository;
    private final SubjectMapper subjectMapper;

    public List<SubjectResDto> getAll() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream().map(subjectMapper::toDto).toList();
    }

    public SubjectResDto getById(int id) {
        Subject subject =  subjectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subject", id));
        return subjectMapper.toDto(subject);
    }

    public SubjectResDto save(SubjectReqDto dto) {
        if (!testRespository.existsById(dto.getTestId())) {
            throw new BadRequestException("Test", dto.getTestId());
        }
        Subject savedSubject = subjectRepository.save(subjectMapper.dtoToEntity(dto));
        return subjectMapper.toDto(savedSubject);
    }

    public SubjectResDto update(int id, SubjectReqDto dto) {
        Subject subjectToUpdate = subjectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subject", id));
        if (!testRespository.existsById(dto.getTestId())) {
            throw new BadRequestException("Test", dto.getTestId());
        }
        subjectToUpdate.setTestId(dto.getTestId());
        subjectToUpdate.setSectionId(dto.getSectionId());
        subjectToUpdate.setTopicId(subjectToUpdate.getTopicId());
        subjectToUpdate.setQsType(dto.getQsType());
        subjectToUpdate.setQsNumber(subjectToUpdate.getQsNumber());
        subjectToUpdate.setOrderNumber(dto.getOrderNumber());
        subjectToUpdate.setUpdateUid(dto.getUserUid());
        Subject updatedSubject = subjectRepository.save(subjectToUpdate);
        return subjectMapper.toDto(updatedSubject);
    }

    public void delete(Integer id) {
        if (!subjectRepository.existsById(id)) {
            throw new NotFoundException("Subject", id);
        }
        subjectRepository.deleteById(id);
    }
}
