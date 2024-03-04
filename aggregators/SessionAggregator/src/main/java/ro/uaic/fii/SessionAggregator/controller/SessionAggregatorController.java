package ro.uaic.fii.SessionAggregator.controller;

import feign.FeignException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.SessionAggregator.dto.SessionDto;
import ro.uaic.fii.SessionAggregator.exceptions.BadRequestException;
import ro.uaic.fii.SessionAggregator.proxy.DomainProxy;
import ro.uaic.fii.SessionAggregator.proxy.SessionProxy;


@RestController
@RequestMapping("/session-aggregator")
public class SessionAggregatorController {

    @Autowired
    DomainProxy domainProxy;
    @Autowired
    SessionProxy sessionProxy;

    Logger logger = LoggerFactory.getLogger(SessionAggregatorController.class);

    @PostMapping
    ResponseEntity<String> addSession(@Valid @RequestBody SessionDto sessionDto) {
        int domainId = sessionDto.domainId();
        try {
            if (isDomainIdValid(domainId)) {
                ResponseEntity<?> postResponse = sessionProxy.addSession(sessionDto);

                if (postResponse.getStatusCode().is2xxSuccessful()) {
                    return ResponseEntity.ok("Session created successfully");
                } else {
                    throw new BadRequestException("Failed to create session");
                }
            } else {
                throw new BadRequestException("Domain with ID: " + domainId + " not found");
            }
        } catch (FeignException.BadRequest err) {
            throw new BadRequestException(err.getMessage());
        } catch (FeignException err) {
            throw new BadRequestException("Bad request");
        } catch (Exception err) {
            throw new BadRequestException(err.getMessage());
        }
    }

    private boolean isDomainIdValid(int domainId) {
        logger.info("Checking if domain with ID: {} exists", domainId);
        try {
            ResponseEntity<?> responseEntity = domainProxy.getDomainById(domainId);
            HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();
            logger.info("Response status: {}", statusCode);
            return statusCode.is2xxSuccessful();
        } catch (FeignException err) {
            logger.error("Error occurred while checking domain with ID: {}", domainId, err);
            return false;
        }
    }
}
