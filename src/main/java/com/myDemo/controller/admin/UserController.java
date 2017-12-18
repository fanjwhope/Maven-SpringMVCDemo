package com.myDemo.controller.admin;

import com.myDemo.common.utils.IpUtil;
import com.myDemo.controller.AbstractBaseController;
import com.myDemo.model.Userinfo;
import com.myDemo.service.UserInfoService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录认证的控制器
 */
@Controller
public class UserController extends AbstractBaseController {
    @Autowired
    UserInfoService userInfoService;
    /**
     * 登录页面
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 登录验证
     * 用户名和密码必须。 是否记住密码非必须
     * @return
     */
    @RequestMapping("/dologin")
    public String dologin(@RequestParam(required = false) String username,
        @RequestParam(required = false) String password,
        @RequestParam(required = false) String remeber_me){
            System.out.println(getRemoteIp());
            //正常的流程
        //1.查询数据库登录
        Userinfo record=new Userinfo();
        record.setUsername(username);
        record.setPassword(password);
        Userinfo user=userInfoService.doLogin(record);
        //2.根据remeber_me 参数半段是否存入cookie
        System.out.println(username+password);
        if (null!= user){
            getSession().setAttribute("user",user);
            return "home";
        }else{
            return "login";
        }
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public void logout(){
        try {
            //删除session
            getSession().removeAttribute("user");
            //删除cookie
            getResponse().sendRedirect("/login");
        } catch (Exception e) {
            logger.error("注销失败",e);
        }

    }

    /**
     * 注册用户页面
     * @return
     */
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 用户注册
     * @return
     */
    @RequestMapping("/doregister")
    @ResponseBody
    public Map doRegister(Userinfo user){
        boolean fag=userInfoService.registerUser(user);
        Map map=new HashMap<>();
        map.put("user",user);
        map.put("msg",fag);
        return map;
    }

    @RequestMapping("userList")
    @ResponseBody
    public List<Userinfo> getUserInfo(){
        return userInfoService.selectAllUser();
    }

}
