package com.glitest.dogwebservice.exception.type;

public class NotFoundException extends RuntimeException {
    private final String message;
    private final String reason;

    public NotFoundException(String message, String reason) {
        this.message = message;
        this.reason = reason;
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression,
                               boolean writableStackTrace,
                               String message1,
                               String reason) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getReason() {
        return reason;
    }
}
