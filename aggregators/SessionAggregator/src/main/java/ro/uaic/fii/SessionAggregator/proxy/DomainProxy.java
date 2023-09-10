package ro.uaic.fii.SessionAggregator.proxy;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.SessionAggregator.dto.SessionDto;
import ro.uaic.fii.SessionAggregator.model.Session;

@FeignClient(name = "domain-proxy", url = "${DOMAIN_SERVICE_HOST:http://127.0.0.1}:5000")
public interface DomainProxy {
    @GetMapping("/domains/check/{id}")
    @ResponseBody
    ResponseEntity<String> isValidDomainId(@PathVariable int id);

    @PostMapping
    ResponseEntity<Session> addSession(@Valid @RequestBody SessionDto sessionDto);
}
