package com.myDemo.controller;

import com.myDemo.model.admin.UserModel;
import com.myDemo.pojo.UserPojo;
import com.myDemo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class RestControllerTest {
    @Autowired
    private UserService userService;
    private static Logger log = Logger.getLogger(RestControllerTest.class);

    @RequestMapping("/test01")
    public String  test01(){
        log.debug("dubug,this is my first ");
        log.info("info,test01 ");
        return "hello ,this is my first spring mvc requset ";
    }

    @RequestMapping("/userList")
    public List<UserModel> findUser() throws Exception{
        return userService.selectAllUser();
    }

   @RequestMapping("/test02")
    public String test02(@RequestBody UserPojo pojo) {
       System.out.println(pojo.getUserBo().toString());
        return "";
    }

    //测试看看能不能获取到 bean
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext.xml");
        RestControllerTest s = cxt.getBean("restControllerTest",RestControllerTest.class);
    }
}
