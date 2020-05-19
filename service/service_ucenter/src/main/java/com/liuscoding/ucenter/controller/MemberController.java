package com.liuscoding.ucenter.controller;


import com.liuscoding.commonutils.JwtUtils;
import com.liuscoding.commonutils.model.vo.MemberOrderVo;
import com.liuscoding.commonutils.vo.ResultVo;
import com.liuscoding.ucenter.entity.Member;
import com.liuscoding.ucenter.model.form.MemberForm;
import com.liuscoding.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 用户注册
     *
     */

    @PostMapping("/register")
    @ApiOperation("会员注册")
    public ResultVo register(@RequestBody MemberForm memberForm){
        memberService.register(memberForm);
        return ResultVo.ok();
    }

    /**
     * 获取会员信息
     *
     */
    @ApiOperation("获取会员信息")
    @GetMapping("/getMemberInfo")
    public ResultVo getMemberInfo(HttpServletRequest request){
        //调用jwt工具类的方法，根据Request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        //根据数据库根据用户id获取用户信息
        Member member = memberService.getById(memberId);
        return ResultVo.ok().data("member",member);
    }

    @ApiOperation("根据会员id获取用户信息")
    @GetMapping("/getMemberInfoOrder/{id}")
    public MemberOrderVo getMemberInfoOrderById(@PathVariable String id){
        Member member = memberService.getById(id);
        MemberOrderVo memberOrderVo = new MemberOrderVo();
        BeanUtils.copyProperties(member,memberOrderVo);
        return memberOrderVo;
    }

    @GetMapping("/countRegister/{day}")
    public Integer countRegister(@PathVariable String day){
        Integer count =  memberService.countRegisterDay(day);
        return count;
    }
}

