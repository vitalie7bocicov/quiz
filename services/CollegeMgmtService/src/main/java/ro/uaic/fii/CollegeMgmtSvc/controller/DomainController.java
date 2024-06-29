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
        return ResponseEntity.ok(domainService.getAll());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<DomainDto> getDomainById(@PathVariable int id) {
        return ResponseEntity.ok(domainService.getById(id));
    }

    @PostMapping
    public ResponseEntity<DomainDto> addDomain(@Valid @RequestBody DomainCreateDto dto) {
        DomainDto savedDomain = domainService.save(dto);
        String uri = getUriString(savedDomain);
        return ResponseEntity.created(URI.create(uri)).body(savedDomain);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DomainDto> updateDomain(@PathVariable int id,
                                               @RequestBody DomainUpdateDto dto) {
        return ResponseEntity.ok(domainService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDomain(@PathVariable int id) {
        domainService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private String getUriString(DomainDto domainDto) {
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(domainDto.getId())
                .toUriString();
    }


    @GetMapping("/test")
    public ResponseEntity<String> calculate() {
        for (long  i = 1; i < 10_000_000; i++) {
            var sqrt = Math.sqrt(i);
        }
        return ResponseEntity.ok("Done");
    }
}
