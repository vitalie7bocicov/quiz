package ro.uaic.fii.CollegeMgmtSvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.DomainCreateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.resDto.DomainDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.mapper.DomainMapper;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.DomainUpdateDto;
import ro.uaic.fii.CollegeMgmtSvc.exceptions.NotFoundException;
import ro.uaic.fii.CollegeMgmtSvc.repository.DomainRepository;
import ro.uaic.fii.CollegeMgmtSvc.repository.model.Domain;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainService {
    private final DomainRepository domainRepository;
    private final DomainMapper domainMapper;

    public DomainDto save(DomainCreateDto createDto)
    {
        Domain savedDomain = domainRepository.save(domainMapper.dtoToEntity(createDto));
        return domainMapper.toDto(savedDomain);
    }

    public List<DomainDto> getAll() {
        List<Domain> domains = domainRepository.findAll();
        return domains.stream().map(domainMapper::toDto).toList();
    }

    public DomainDto getById(int id) {
        Domain domain = domainRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Domain", id));
        return domainMapper.toDto(domain);
    }

    public DomainDto update(int id, DomainUpdateDto updateDto) {
        Domain existingDomain = domainRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Domain", id));
        existingDomain.setAbbr(updateDto.abbr());
        existingDomain.setName(updateDto.name());
        existingDomain.setUpdateUid(updateDto.userUid());
        Domain updatedDomain = domainRepository.save(existingDomain);
        return domainMapper.toDto(updatedDomain);
    }

    public void deleteById(int id) {
        Domain domain = domainRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Domain", id));
        domainRepository.delete(domain);
    }
}
