package ro.uaic.fii.DomainService.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.DomainService.convertor.DomainDtoToModel;
import ro.uaic.fii.DomainService.dto.DomainDto;
import ro.uaic.fii.DomainService.model.Domain;
import ro.uaic.fii.DomainService.service.DomainService;

import java.util.List;

@RestController
@RequestMapping("/domains")
public class DomainController {
    private final DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }
    @PostMapping
    public ResponseEntity<Domain> addDomain(@Valid @RequestBody DomainDto domainDto)
    {
        Domain domain = DomainDtoToModel.convert(domainDto);
        Domain savedDomain = domainService.saveDomain(domain);
        return ResponseEntity.ok(savedDomain);
    }

    @GetMapping
    public ResponseEntity<List<Domain>> getDomains()
    {
        List<Domain> domains = domainService.getDomains();
        return ResponseEntity.ok(domains);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Domain> getDomainById(@PathVariable int id)
    {
        Domain domain = domainService.getDomainById(id);
        return ResponseEntity.ok(domain);
    }

    @GetMapping({"/check/{id}"})
    public ResponseEntity<String> isValidDomainId(@PathVariable int id)
    {
        Domain domain = domainService.getDomainById(id);
        return ResponseEntity.ok("Domain with ID: " + id + " is valid");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Domain> updateDomain(@PathVariable int id,
                                               @RequestBody DomainDto domainDto)
    {
        Domain updateDomain = DomainDtoToModel.convert(domainDto);
        Domain domain = domainService.updateDomain(id, updateDomain);
        return ResponseEntity.ok(domain);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDomain(@PathVariable int id)
    {
        domainService.deleteDomainById(id);
        return ResponseEntity.ok("Domain with ID: " + id + " deleted.");
    }
}
