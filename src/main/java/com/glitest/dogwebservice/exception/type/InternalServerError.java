package com.glitest.dogwebservice.exception.type;

public class InternalServerError extends RuntimeException {
    private final String message;
    private final String reason;

    public InternalServerError(String message, String reason) {
        this.message = message;
        this.reason = reason;
    }

    public InternalServerError(String message, Throwable cause, boolean enableSuppression,
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
