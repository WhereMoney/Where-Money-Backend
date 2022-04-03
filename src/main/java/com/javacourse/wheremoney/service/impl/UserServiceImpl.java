package com.javacourse.wheremoney.service.impl;

import com.javacourse.wheremoney.entity.User;
import com.javacourse.wheremoney.mapper.UserMapper;
import com.javacourse.wheremoney.service.UserService;
import com.javacourse.wheremoney.service.ex.*;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void registerUser(User user) {
        User sameName = userMapper.findByUserName(user.getUserName());
        if (sameName != null)
        {
            throw new UsernameOccupiedException();
        }
        //用户密码加密
        String hashedPassword = MD5Encode.encode(user.getHashedPassword());
        user.setHashedPassword(hashedPassword);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setCreateTime(timestamp);
        Integer rowI = userMapper.insertUser(user);
        if (rowI!=1)
        {
            throw new InsertException();
        }
    }

    @Override
    public void changeUsername(Integer id, String newName) {
        User user = userMapper.findByUserId(id);
        if(user == null){
            throw new UserMissingException();
        }
        user.setUserName(newName);
        Integer rowU =  userMapper.updateUser(user);
        if (rowU != 1)
        {
            throw new UpdateException();
        }
    }

    @Override
    public void changePassword(Integer id, String newPassword)  {
        String hashedPassword = MD5Encode.encode(newPassword);
        User user = userMapper.findByUserId(id);
        if(user == null){
            throw new UserMissingException();
        }
        user.setHashedPassword(hashedPassword);
        Integer rowU =  userMapper.updateUser(user);
        if (rowU != 1)
        {
            throw new UpdateException();
        }
    }

    @Override
    public Integer login(String userName, String password) {
        User trueUser = userMapper.findByUserName(userName); //通过用户名找到数据库中的信息
        if(trueUser == null){
            throw new UserMissingException();
        }
        String correctPassword = trueUser.getHashedPassword();
        String nowPassword = MD5Encode.encode(password);
        if(!Objects.equals(correctPassword, nowPassword))
        {
            throw new PasswordErrorException();
        }
        return 1;

    }


}
