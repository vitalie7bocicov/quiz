package ro.uaic.fii.SessionAggregator.proxy;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.SessionAggregator.dto.SessionDto;

@FeignClient(name = "session-proxy", url = "localhost:5001/sessions")
public interface SessionProxy {
    @PostMapping
    ResponseEntity<?> addSession(@Valid @RequestBody SessionDto sessionDto);
}
