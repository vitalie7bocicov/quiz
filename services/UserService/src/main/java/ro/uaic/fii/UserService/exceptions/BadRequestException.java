package ro.uaic.fii.UserService.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String resource, Integer id) {
        super("Invalid parameter for '%s' with id '%d'.".formatted(resource, id));
    }

    public BadRequestException(String user, String account) {
        super("Invalid parameter for '%s' with account '%s'.".formatted(user, account));
    }
}
