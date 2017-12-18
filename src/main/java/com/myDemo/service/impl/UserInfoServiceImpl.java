package com.myDemo.service.impl;

import com.myDemo.mapper.UserinfoMapper;
import com.myDemo.model.Userinfo;
import com.myDemo.service.UserInfoService;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl  implements UserInfoService{
    private static Logger log = Logger.getLogger(UserInfoServiceImpl.class);
    @Autowired
    UserinfoMapper userinfoMapper;

    @Override
    public Userinfo doLogin(Userinfo record) {
       Userinfo user=userinfoMapper.selectUser(record);
       //没有该用户
       return user;
    }

    @Override
    public Userinfo selectByPrimaryKey(Integer userid) {
        return userinfoMapper.selectByPrimaryKey(userid);
    }

    @Override
    public boolean registerUser(Userinfo record) {
        //首先判断用户是否存在 根据usename
        Long count=userinfoMapper.selectCountByUserName(record.getUsername());
        if (count>0){
            log.debug(record.getUsername()+"用戶已經存在！");
            return false;
        }else {
            int a = userinfoMapper.insertSelective(record);
            if (a == 1) {
                log.debug("注冊成功！");
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean deleteUserById(Integer userid) {
        if (userinfoMapper.deleteByPrimaryKey(userid)>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Userinfo> selectAllUser() {
       return userinfoMapper.selectAllUser();
    }
}
