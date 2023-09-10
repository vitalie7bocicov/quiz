package ro.uaic.fii.SessionAggregator.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.uaic.fii.SessionAggregator.exceptions.InternalErrorException;
import ro.uaic.fii.SessionAggregator.exceptions.NotFoundException;

@RestControllerAdvice
public class InternalErrorAdvice {

    @ExceptionHandler(InternalErrorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String badRequestHandler(InternalErrorException ex) {
        return ex.getMessage();
    }
}
