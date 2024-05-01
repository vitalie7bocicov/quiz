package ro.uaic.fii.CollegeMgmtSvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.DomainUpdateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.DomainCreateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.resDto.DomainDto;
import ro.uaic.fii.CollegeMgmtSvc.service.DomainService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/domains")
@RequiredArgsConstructor
public class DomainController {
    private final DomainService domainService;
    private final HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<DomainDto>> getDomains() {
        return ResponseEntity.ok(domainService.getDomains());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<DomainDto> getDomainById(@PathVariable int id) {
        return ResponseEntity.ok(domainService.getDomainById(id));
    }

    @PostMapping
    public ResponseEntity<DomainDto> addDomain(@Valid @RequestBody DomainCreateDto dto) {
        DomainDto savedDomain = domainService.saveDomain(dto);
        String uri = getUriString(savedDomain);
        return ResponseEntity.created(URI.create(uri)).body(savedDomain);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DomainDto> updateDomain(@PathVariable int id,
                                               @RequestBody DomainUpdateDto dto) {
        return ResponseEntity.ok(domainService.updateDomain(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDomain(@PathVariable int id) {
        domainService.deleteDomainById(id);
        return ResponseEntity.noContent().build();
    }

    private String getUriString(DomainDto domainDto) {
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(domainDto.getId())
                .toUriString();
    }
}
