package com.myDemo.common.interceptor;

import com.myDemo.common.utils.IpUtil;
import com.myDemo.model.Userinfo;
import com.sun.javafx.binding.StringFormatter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录认证的拦截器 实现HandlerInterceptor 接口
 */
@Component
public class LoginInterceptor implements HandlerInterceptor{

    private static Logger log = Logger.getLogger(LoginInterceptor.class);

    /**
     * Handler执行之前调用这个方法
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取请求的URL
        String url = httpServletRequest.getRequestURI();
        System.out.println("被拦截的URL"+ url);
        //URL:login.jsp是公开的;这个demo是除了login.jsp 和注册页面register.jsp 是可以公开访问的，其它的URL都进行拦截控制
        if(url.indexOf("login")>=0 || url.indexOf("register")>=0){
            return true;
        }
        System.out.println(StringFormatter.format("用户访问地址: {}, 来路地址: {}", url, IpUtil.getIpAddrByRequest(httpServletRequest)));
        //log.debug(StringFormatter.format("用户访问地址: {}, 来路地址: {}", url, IpUtil.getIpAddrByRequest(httpServletRequest)));
        //获取Session
        HttpSession session = httpServletRequest.getSession();
        Userinfo user = (Userinfo)session.getAttribute("user");
        System.out.println(user);
        if(user != null){
            return true;
        }
        //不符合条件的，跳转到登录界面 /WEB-INF/views/login.jsp
        httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);
        return false;
    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("===========LoginInterceptor postHandle");
    }

    /**
     * Handler执行完成之后调用这个方法
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("===========LoginInterceptor afterCompletion");
    }
}
