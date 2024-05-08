package ro.uaic.fii.UserService.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String resource, int id) {
        super("Could not find %s with id: %d".formatted(resource, id));
    }

    public NotFoundException(String user, UUID id) {
        super("Could not find %s with id: %s".formatted(user, id));
    }
}
