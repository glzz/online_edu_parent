package com.atguigu.hander;

import com.atguigu.edu.exception.EduException;
import com.atguigu.edu.response.RetVal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: 了飘尘
 * @date: 2022/3/31 23:19
 * @version: 1.0
 * @Description: 统一异常处理类
 */
@ControllerAdvice
public class GlobaException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RetVal error(Exception e) {
        e.printStackTrace();
        System.out.println("全局异常生效了");
        return RetVal.error().message("全局异常生效了");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public RetVal error2(ArithmeticException e) {
        e.printStackTrace();
        System.out.println("特殊异常生效了");
        return RetVal.error().message("特殊异常生效了");
    }

    @ExceptionHandler(EduException.class)
    @ResponseBody
    public RetVal error2(EduException e) {
        e.printStackTrace();
        System.out.println("自定义异常生效了");
        return RetVal.error().message(e.getMessage());
    }

}
