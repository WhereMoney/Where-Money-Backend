package com.javacourse.wheremoney.service.ex;
/** 用户名被占用*/
public class UsernameOccupiedException extends ServiceExecption{
    public UsernameOccupiedException() {
        super();
    }

    public UsernameOccupiedException(String message) {
        super(message);
    }

    public UsernameOccupiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameOccupiedException(Throwable cause) {
        super(cause);
    }

    protected UsernameOccupiedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
