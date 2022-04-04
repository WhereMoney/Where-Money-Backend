package shuhuai.wheremoney.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shuhuai.wheremoney.entity.User;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Resource
    private UserMapper userMapper;

    @Test
    public void insertUser() {
        User user = new User("1", "hashed1");
        Integer rows = userMapper.insertUserSelective(user);
        System.out.println(rows);
    }
}