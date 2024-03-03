package ro.uaic.fii.SessionAggregator.controller;

import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.SessionAggregator.dto.SessionDto;
import ro.uaic.fii.SessionAggregator.exceptions.BadRequestException;
import ro.uaic.fii.SessionAggregator.model.Session;
import ro.uaic.fii.SessionAggregator.proxy.DomainProxy;
import ro.uaic.fii.SessionAggregator.proxy.SessionProxy;


@RestController
@RequestMapping("/session-aggregator")
public class SessionAggregatorController {

    @Autowired
    DomainProxy domainProxy;
    @Autowired
    SessionProxy sessionProxy;

    @PostMapping
    ResponseEntity<Session> addSession(@Valid @RequestBody SessionDto sessionDto) {
        try {
            int domainId = sessionDto.domainId();
            domainProxy.isValidDomainId(domainId);
            Session session = sessionProxy.addSession(sessionDto).getBody();
            return ResponseEntity.ok(session);
        } catch (FeignException.NotFound err) {
            throw new BadRequestException("Domain with ID: " + sessionDto.domainId() + " not found.");
        } catch (FeignException.BadRequest err ) {
            throw new BadRequestException(err.getMessage());
        } catch (FeignException e) {
            throw new BadRequestException("Bad request");
        }
    }
}
