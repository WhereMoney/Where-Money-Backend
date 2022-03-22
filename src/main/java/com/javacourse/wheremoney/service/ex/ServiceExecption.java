package com.javacourse.wheremoney.service.ex;
/** 业务层异常基类*/
public class ServiceExecption extends RuntimeException {
    public ServiceExecption() {
        super();
    }
    // 抛出带信息异常
    public ServiceExecption(String message) {
        super(message);
    }
    // 抛出带信息异常及对象
    public ServiceExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceExecption(Throwable cause) {
        super(cause);
    }

    protected ServiceExecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
