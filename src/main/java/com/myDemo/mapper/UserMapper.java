package com.myDemo.mapper;

import com.myDemo.model.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAllUser()throws Exception;
    int insertUser(User user)throws Exception;
}
