package com.liuscoding.edu.controller;

import com.liuscoding.commonutils.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @className: LoginController
 * @description: 登录处理
 * @author: liusCoding
 * @create: 2020-05-08 14:03
 */
@Api(tags = "用户登录")
@RestController
@RequestMapping("/edu/user")
//@CrossOrigin//解决跨域
public class LoginController {


    /**
     * 登录
     * @return ResultVo
     */
    @PostMapping("/login")
    public ResultVo login(){
        return ResultVo.ok().data("token","admin");
    }

    /**
     * 获取用户信息
     * @return ResultVo
     */
    @GetMapping("/info")
    public ResultVo info(){
        return ResultVo.ok().data("roles","[admin]").data("name","admin")
                .data("avatar","https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1023235865,1560869099&fm=26&gp=0.jpg");

    }
}
