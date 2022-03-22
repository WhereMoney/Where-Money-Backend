package com.javacourse.wheremoney.controller;

import com.javacourse.wheremoney.service.ex.*;
import com.javacourse.wheremoney.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
    public static final int OK = 200;

    @ExceptionHandler(ServiceExecption.class)
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof UsernameOccupiedException){
            result.setState(4000);
            result.setMessage("用户名被占用");
        }
        else if(e instanceof InsertException){
            result.setState(5000);
            result.setMessage("插入异常");
        }
        else if(e instanceof UpdateException){
            result.setState(6000);
            result.setMessage("数据更新异常");
        }
        else if(e instanceof UserMissingException){
            result.setState(422);
            result.setMessage("用户不存在");
        }
        else if (e instanceof PasswordErrorException){
            result.setState(401);
            result.setMessage("密码错误");
        }
        return result;
    }
}
