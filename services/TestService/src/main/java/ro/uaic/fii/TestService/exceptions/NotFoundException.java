package ro.uaic.fii.TestService.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String resource, int id) {
        super("Could not find %s with id: %d".formatted(resource, id));
    }
}
