package ro.uaic.fii.UserAggregator.proxy;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ro.uaic.fii.SessionAggregator.dto.SessionDto;
import ro.uaic.fii.SessionAggregator.model.Session;

@FeignClient(name = "session-proxy", url = "${SESSION_SERVICE_HOST:http://127.0.0.1}:5001")
public interface SessionProxy {
    @PostMapping("/sessions")
    ResponseEntity<Session> addSession(@Valid @RequestBody SessionDto sessionDto);
}
