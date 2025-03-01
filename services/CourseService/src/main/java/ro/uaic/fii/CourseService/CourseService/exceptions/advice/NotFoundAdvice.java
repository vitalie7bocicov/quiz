package ro.uaic.fii.CourseService.CourseService.exceptions.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.uaic.fii.CourseService.CourseService.exceptions.NotFoundException;

@RestControllerAdvice
public class NotFoundAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String badRequestHandler(NotFoundException ex) {
        return ex.getMessage();
    }
}
