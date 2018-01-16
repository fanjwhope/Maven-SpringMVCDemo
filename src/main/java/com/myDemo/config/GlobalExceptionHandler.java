package com.myDemo.config;

import com.myDemo.common.bean.BaseResponse;
import com.myDemo.common.bean.BussinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常处理类
 *
 * @author
 * @create 2018/1/16 13:27
 **/
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BussinessException.class)
    public BaseResponse bussinessException(BussinessException e) {
        System.out.println("嗨，这是全局异常处理的自定义异常。");
        log.error("业务异常：", e.getMessage());
        return BaseResponse.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse exception(Exception e) {
        System.out.println("嗨，这是全局异常处理的Exception异常。");
        log.error("异常消息：", e);
        return BaseResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
