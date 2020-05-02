package com.liuscoding.servicebase.exceptionhandler;

import com.liuscoding.commonutils.vo.ResultVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @className: GlobalExceptionHandler
 * @description: 全局异常处理
 * @author: liusCoding
 * @create: 2020-05-02 12:23
 */

@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     *   指定出现什么异常执行这个方法
     */
    @ExceptionHandler(Exception.class)
    public ResultVo error(Exception e) {
        e.printStackTrace();
        return ResultVo.error().message("执行了全局异常处理..");
    }
}
