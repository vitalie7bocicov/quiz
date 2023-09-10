package ro.uaic.fii.DomainService.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.uaic.fii.DomainService.exceptions.BadRequestException;
import ro.uaic.fii.DomainService.exceptions.DomainNotFoundException;

@RestControllerAdvice
public class NotFoundAdvice {

    @ExceptionHandler(DomainNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String badRequestHandler(DomainNotFoundException ex) {
        return ex.getMessage();
    }
}
