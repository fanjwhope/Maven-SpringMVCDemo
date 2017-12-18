package com.myDemo.mapper;

import com.myDemo.model.admin.UserModel;

import java.util.List;

public interface UserMapper {
    List<UserModel> selectAllUser()throws Exception;
    int insertUser(UserModel user)throws Exception;
}
