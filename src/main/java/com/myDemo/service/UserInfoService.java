package com.myDemo.service;

import com.myDemo.model.Userinfo;

import java.util.List;

/**
 * 用户管理services
 */
public interface UserInfoService {

    //用户登录验证
    Userinfo doLogin(Userinfo record);
    //根据id主键查询用户信息
    Userinfo selectByPrimaryKey(Integer userid);
    //注册用户信息
    boolean registerUser(Userinfo record);
    //根据Id删除用户信息
    boolean deleteUserById(Integer userid);

    //批量删除用户信息

    //查询所用用户信息
    List<Userinfo> selectAllUser();

    //分页查询用户信息


}
