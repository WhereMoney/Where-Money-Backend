package shuhuai.wheremoney.mapper;

import shuhuai.wheremoney.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
     public void insertUser()
    {
        User user = new User("1","hashed1");
        Integer rows = userMapper.insertUser(user);
        System.out.println(rows);
    }

}
