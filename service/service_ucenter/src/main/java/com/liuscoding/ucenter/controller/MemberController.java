package com.liuscoding.ucenter.controller;


import com.liuscoding.commonutils.vo.ResultVo;
import com.liuscoding.ucenter.model.form.MemberForm;
import com.liuscoding.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-16
 */
@Api(tags = "会员管理 ")
@RestController
@RequestMapping("/ucenter/member")
@CrossOrigin
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    /**
     * 用户登录
     */

    @PostMapping("/login")
    @ApiOperation("会员登录")
    public ResultVo login(@RequestBody MemberForm memberForm){
        //memberForm 对象封装手机号和密码

        //返回token 值,使用jwt生成
        String token = memberService.login(memberForm);

        return ResultVo.ok().data("token",token);
    }
}

