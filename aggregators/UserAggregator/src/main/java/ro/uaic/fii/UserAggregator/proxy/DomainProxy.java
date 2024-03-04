package ro.uaic.fii.UserAggregator.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "domain-proxy", url = "localhost:5000")
public interface DomainProxy {
    @GetMapping("/domains/check/{id}")
    @ResponseBody
    ResponseEntity<String> isValidDomainId(@PathVariable int id);

    @PostMapping
    ResponseEntity<Session> addSession(@Valid @RequestBody SessionDto sessionDto);
}
