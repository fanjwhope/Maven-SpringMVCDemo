package com.myDemo.service.impl;

import com.myDemo.mapper.UserMapper;
import com.myDemo.model.User;
import com.myDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) throws Exception {
        return userMapper.insertUser(user);
    }
    @Override
    public List<User> selectAllUser() throws Exception {
       return userMapper.selectAllUser();
    }
}
