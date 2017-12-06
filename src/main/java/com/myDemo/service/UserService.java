package com.myDemo.service;

import com.myDemo.model.User;

import java.util.List;

public interface UserService {
    int insertUser(User user)throws Exception;
    List<User> selectAllUser() throws Exception;
}
