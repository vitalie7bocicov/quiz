package ro.uaic.fii.CollegeMgmtSvc.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String resource, Integer id) {
        super("Invalid parameter for '%s' with id '%d'.".formatted(resource, id));
    }
}