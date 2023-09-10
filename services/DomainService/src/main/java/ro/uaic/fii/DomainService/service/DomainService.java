package ro.uaic.fii.DomainService.service;

import org.springframework.stereotype.Service;
import ro.uaic.fii.DomainService.exceptions.BadRequestException;
import ro.uaic.fii.DomainService.exceptions.DomainNotFoundException;
import ro.uaic.fii.DomainService.model.Domain;
import ro.uaic.fii.DomainService.repository.DomainRepository;

import java.util.List;

@Service
public class DomainService {
    private final DomainRepository repository;
    public DomainService(DomainRepository repository) {
        this.repository = repository;
    }

    public Domain saveDomain(Domain domain)
    {
        try {
            return repository.save(domain);
        }
        catch (Exception e)
        {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<Domain> getDomains() {
        return repository.findAll();
    }

    public Domain getDomainById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainNotFoundException("Domain with ID: " + id + " not found."));
    }

    public Domain updateDomain(int id, Domain updateDomain) throws DomainNotFoundException{
        Domain existingDomain = getDomainById(id);
        existingDomain.setAbbr(updateDomain.getAbbr());
        existingDomain.setName(updateDomain.getName());
        existingDomain.setUpdateUid(updateDomain.getUpdateUid());
        return repository.save(existingDomain);
    }

    public void deleteDomainById(int id) {
        Domain domain = getDomainById(id);
        repository.delete(domain);
    }
}
