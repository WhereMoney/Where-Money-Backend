package shuhuai.wheremoney.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Resource
    private UserService userService;

    @Test
    public void registerUser() {
        userService.register("a", "123");
    }
}