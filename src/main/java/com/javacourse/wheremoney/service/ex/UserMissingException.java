package com.javacourse.wheremoney.service.ex;

public class UserMissingException extends ServiceExecption{
    public UserMissingException() {
        super();
    }

    public UserMissingException(String message) {
        super(message);
    }

    public UserMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserMissingException(Throwable cause) {
        super(cause);
    }

    protected UserMissingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
