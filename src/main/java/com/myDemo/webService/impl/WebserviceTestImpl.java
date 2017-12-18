package com.myDemo.webService.impl;

import com.myDemo.webService.WebserviceTest;

import javax.jws.WebService;


/**
 * 利用cxf 发布webservice 需要在接口实现部分。定义该实现类 实现的 接口
 * endpointInterface="com.myDemo.webService.WebserviceTest"
 *
 */
@WebService(endpointInterface="com.myDemo.webService.WebserviceTest",serviceName="WebserviceTest")
public class WebserviceTestImpl implements WebserviceTest {
    @Override
    public String sayHello(String name) {
        System.out.println("hello"+name);
        return name;
    }
}
