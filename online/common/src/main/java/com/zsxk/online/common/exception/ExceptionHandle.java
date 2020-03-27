package com.zsxk.online.common.exception;

import com.zsxk.online.common.response.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice  //请求出现异常是会找着个注解
public class ExceptionHandle {

    /**
     * 统一异常信息处理
     * **/
    @ExceptionHandler(Exception.class)  //异常出现会强求该方法,当然也可以指定比较明确的异常
    @ResponseBody
    public Result exceptonInfo(Exception e) {
        e.printStackTrace();
        return Result.error().message("出现异常： " + e.getMessage());


    }

}
