package com.myDemo.service.impl;

import com.myDemo.mapper.UserMapper;
import com.myDemo.model.admin.UserModel;
import com.myDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUser(UserModel user) throws Exception {
        return userMapper.insertUser(user);
    }
    @Override
    public List<UserModel> selectAllUser() throws Exception {
       return userMapper.selectAllUser();
    }
}
