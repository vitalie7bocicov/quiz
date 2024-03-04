package ro.uaic.fii.SessionAggregator.proxy;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.SessionAggregator.dto.SessionDto;

@FeignClient(name = "domain-proxy", url = "localhost:5000/domains")
public interface DomainProxy {
    @GetMapping({"/{id}"})
    ResponseEntity<?> getDomainById(@PathVariable int id);

    @PostMapping
    ResponseEntity<?> addSession(@Valid @RequestBody SessionDto sessionDto);
}
