package com.javacourse.wheremoney.service;

import com.javacourse.wheremoney.entity.User;

public interface UserService {
    void registerUser(User user);

    void changeUsername(Integer id,String newName);

    void changePassword(Integer id,String newPassword);

    Integer loginIn(String userName,String password);
}
