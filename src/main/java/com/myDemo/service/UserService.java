package com.myDemo.service;

import com.myDemo.model.admin.UserModel;

import java.util.List;

public interface UserService {
    int insertUser(UserModel user)throws Exception;
    List<UserModel> selectAllUser() throws Exception;
}
