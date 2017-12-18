package com.myDemo.webService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface WebserviceTest {
    //使用@WebMethod注解标注WebServiceI接口中的方法
    @WebMethod
    String sayHello(@WebParam(name="name") String name);
}
