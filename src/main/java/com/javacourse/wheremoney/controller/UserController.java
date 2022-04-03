package com.javacourse.wheremoney.controller;

import com.javacourse.wheremoney.entity.User;
import com.javacourse.wheremoney.service.UserService;
import com.javacourse.wheremoney.util.JsonResult;
import com.javacourse.wheremoney.util.TokenValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/users")
@Api(tags = "用户模块")
public class UserController extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private TokenValidator tokenValidator;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public JsonResult register(String userName, String password) {
        User newUser = new User(userName, password);
        userService.registerUser(newUser);
        return new JsonResult(200, "创建成功", null);
    }

    @RequestMapping(value = "/change-name", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户名")
    public JsonResult changeUsername(Integer id, String newName) {
        userService.changeUsername(id, newName);
        return new JsonResult(200, "修改成功", null);
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    @ApiOperation(value = "修改密码")
    public JsonResult changePassword(Integer id, String newPassword) {
        userService.changePassword(id, newPassword);
        return new JsonResult(200, "修改成功", null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    public JsonResult login(String userName, String password) {
        userService.login(userName, password);
        String token = tokenValidator.getToken(userName);
        return new JsonResult(200, "登陆成功", token);
    }
}
