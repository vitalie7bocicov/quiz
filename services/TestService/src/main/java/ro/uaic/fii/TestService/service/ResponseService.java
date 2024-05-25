package ro.uaic.fii.TestService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.fii.TestService.dto.mapper.ResponseMapper;
import ro.uaic.fii.TestService.dto.reqDto.ResponseReqDto;
import ro.uaic.fii.TestService.dto.resDto.ResponseResDto;
import ro.uaic.fii.TestService.exceptions.BadRequestException;
import ro.uaic.fii.TestService.exceptions.NotFoundException;
import ro.uaic.fii.TestService.repository.model.Response;
import ro.uaic.fii.TestService.repository.ParticipantRepository;
import ro.uaic.fii.TestService.repository.QuestionRepository;
import ro.uaic.fii.TestService.repository.ResponseRepository;

import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Service
@RequiredArgsConstructor
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final ParticipantRepository participantRepository;
    private final QuestionRepository questionRepository;
    private final ResponseMapper responseMapper;

    public List<ResponseResDto> getAll() {
        List<Response> responses = responseRepository.findAll();
        return responses.stream().map(responseMapper::toDto).toList();
    }

    public ResponseResDto getById(int id) {
        Response response =  responseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Response", id));
        return responseMapper.toDto(response);
    }

    public ResponseResDto save(ResponseReqDto dto) {
        Response savedResponse = responseRepository.save(responseMapper.dtoToEntity(dto));
        return responseMapper.toDto(savedResponse);
    }

    public ResponseResDto update(int id, ResponseReqDto dto) {
        Response responseToUpdate = responseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Response", id));
        if (!participantRepository.existsById(dto.getParticipantId())) {
            throw new BadRequestException("Participant", dto.getParticipantId());
        }
        if (isNotEmpty(dto.getBaseQuestionId()) && !questionRepository.existsById(dto.getBaseQuestionId())) {
            throw new BadRequestException("Question", dto.getBaseQuestionId());
        }
        responseToUpdate.setParticipantId(dto.getParticipantId());
        responseToUpdate.setOrderNumber(dto.getOrderNumber());
        responseToUpdate.setBaseQuestionId(dto.getBaseQuestionId());
        responseToUpdate.setQuestion(dto.getQuestion());
        responseToUpdate.setAnswer(dto.getAnswer());
        responseToUpdate.setPoints(dto.getPoints());
        responseToUpdate.setIpAddress(dto.getIpAddress());
        responseToUpdate.setUpdateUid(dto.getUserUid());
        Response updatedResponse = responseRepository.save(responseToUpdate);
        return responseMapper.toDto(updatedResponse);
    }

    public void delete(Integer id) {
        if (!responseRepository.existsById(id)) {
            throw new NotFoundException("Response", id);
        }
        responseRepository.deleteById(id);
    }
}
