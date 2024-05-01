package ro.uaic.fii.CollegeMgmtSvc.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
