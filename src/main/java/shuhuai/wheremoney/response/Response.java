package shuhuai.wheremoney.response;

import java.io.Serializable;

public class Response implements Serializable {
    private Integer code;
    private String message;
    private Object data;

    public Response() {
        code = 0;
        message = "";
        data = null;
    }

    public Response(Integer code) {
        this.code = code;
    }

    public Response(Throwable error) {
        this.message = error.getMessage();
    }

    public Response(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Response(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
