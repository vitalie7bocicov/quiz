package ro.uaic.fii.CourseService.CourseService.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String resource, Integer id, String message) {
        super("Bad request on " + resource + " with id " + id + ": " + message);
    }

}
