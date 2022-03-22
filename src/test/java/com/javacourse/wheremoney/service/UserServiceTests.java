package com.javacourse.wheremoney.service;

import com.javacourse.wheremoney.entity.User;
import com.javacourse.wheremoney.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private UserService userService;
    @Test
     public void registerUser()
    {
        User user = new User("a","123");
        userService.registerUser(user);
    }
    @Test
    public void changeUserpassword()
    {
        String newName = "abcd";
        Integer id = 8;
        userService.changePassword(id,newName);
    }

}
