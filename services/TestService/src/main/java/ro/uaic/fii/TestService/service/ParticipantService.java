package ro.uaic.fii.TestService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.fii.TestService.dto.mapper.ParticipantMapper;
import ro.uaic.fii.TestService.dto.reqDto.ParticipantReqDto;
import ro.uaic.fii.TestService.dto.resDto.ParticipantResDto;
import ro.uaic.fii.TestService.exceptions.BadRequestException;
import ro.uaic.fii.TestService.exceptions.NotFoundException;
import ro.uaic.fii.TestService.repository.model.Participant;
import ro.uaic.fii.TestService.repository.ParticipantRepository;
import ro.uaic.fii.TestService.repository.TestRespository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final TestRespository testRepository;
    private final ParticipantMapper participantMapper;


    public ParticipantResDto getById(int id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Participant", id));
        return participantMapper.toDto(participant);
    }

    public ParticipantResDto save(ParticipantReqDto dto) {
        if (!testRepository.existsById(dto.getTestId())) {
            throw new BadRequestException("Test", dto.getTestId());
        }
        Participant savedParticipant = participantRepository.save(participantMapper.dtoToEntity(dto));
        return participantMapper.toDto(savedParticipant);
    }

    public List<ParticipantResDto> getAll() {
        List<Participant> participants = participantRepository.findAll();
        return participants.stream().map(participantMapper::toDto).toList();
    }

    public ParticipantResDto update(int id, ParticipantReqDto dto) {
        Participant participantToUpdate = participantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Participant", id));
        if (!testRepository.existsById(dto.getTestId())) {
            throw new BadRequestException("Test", dto.getTestId());
        }
        participantToUpdate.setTestId(dto.getTestId());
        participantToUpdate.setIpAddress(dto.getIpAddress());
        participantToUpdate.setLocale(dto.getLocale());
        participantToUpdate.setPoints(dto.getPoints());
        participantToUpdate.setActive(dto.isActive());
        participantToUpdate.setUpdateUid(dto.getUserUid());
        Participant updatedParticipant = participantRepository.save(participantToUpdate);
        return participantMapper.toDto(updatedParticipant);
    }

    public void delete(int id) {
        if (!participantRepository.existsById(id)) {
            throw new NotFoundException("Participant", id);
        }
        participantRepository.deleteById(id);
    }
}
