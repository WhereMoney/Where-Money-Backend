package com.javacourse.wheremoney.controller;

import com.javacourse.wheremoney.entity.User;
import com.javacourse.wheremoney.service.UserService;
import com.javacourse.wheremoney.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Api(tags = "用户模块")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public JsonResult<Void> register(String userName, String password) {
        User newUser = new User(userName, password);
        userService.registerUser(newUser);
        JsonResult<Void> result = new JsonResult<>(OK);
        result.setMessage("创建成功");
        return result;
    }

    @RequestMapping(value = "/change-name", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户名")
    public JsonResult<Void> changeUsername(Integer id, String newName) {
        userService.changeUsername(id,newName);
        JsonResult<Void> result = new JsonResult<>(OK);
        result.setMessage("修改成功");
        return result;
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    @ApiOperation(value = "修改密码")
    public JsonResult<Void> changePassword(Integer id, String newName) {
        userService.changePassword(id,newName);
        JsonResult<Void> result = new JsonResult<>(OK);
        result.setMessage("修改成功");
        return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    public JsonResult<Void> login(String userName, String password) {
        userService.loginIn(userName,password);
        JsonResult<Void> result = new JsonResult<>(OK);
        result.setMessage("登录成功");
        return result;
    }
}
