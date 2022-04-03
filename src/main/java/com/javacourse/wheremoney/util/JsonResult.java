package com.javacourse.wheremoney.util;

import org.apache.ibatis.ognl.ObjectElementsAccessor;

import java.io.Serializable;

public class JsonResult implements Serializable {
    private Integer state;
    private String message;
    private  Object data;

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state,String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public JsonResult() {

    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
