package ro.uaic.fii.CollegeMgmtSvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.fii.CollegeMgmtSvc.dto.mapper.SessionMapper;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.SessionCreateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.SessionUpdateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.resDto.SessionDto;
import ro.uaic.fii.CollegeMgmtSvc.exceptions.BadRequestException;
import ro.uaic.fii.CollegeMgmtSvc.exceptions.NotFoundException;
import ro.uaic.fii.CollegeMgmtSvc.repository.DomainRepository;
import ro.uaic.fii.CollegeMgmtSvc.repository.SessionRepository;
import ro.uaic.fii.CollegeMgmtSvc.repository.model.Session;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;
    private final DomainRepository domainRepository;
    public SessionDto save(SessionCreateDto createDto) {

        if (!domainRepository.existsById(createDto.domainId())) {
            throw new BadRequestException("Domain", createDto.domainId());
        }
        Session savedSession = sessionRepository.save(sessionMapper.dtoToEntity(createDto));
        return sessionMapper.toDto(savedSession);
    }

    public List<SessionDto> getAll() {
       List<Session> sessions = sessionRepository.findAll();
       return sessions.stream().map(sessionMapper::toDto).toList();
    }

    public SessionDto getById(int id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Session", id));
        return sessionMapper.toDto(session);
    }

    public SessionDto update(int id, SessionUpdateDto updateDto) {
        Session existingSession = sessionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Session", id));

        if (!domainRepository.existsById(updateDto.domainId())) {
            throw new BadRequestException("Domain", updateDto.domainId());
        }

        existingSession.setDomainId(updateDto.domainId());
        existingSession.setName(updateDto.name());
        existingSession.setActive(updateDto.active());
        existingSession.setNotes(updateDto.notes());
        existingSession.setUpdateUid(updateDto.userUid());
        Session updateSession = sessionRepository.save(existingSession);
        return sessionMapper.toDto(updateSession);
    }

    public void deleteById(int id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Session", id));
        sessionRepository.delete(session);
    }
}
