package com.liuscoding.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuscoding.commonutils.JwtUtils;
import com.liuscoding.commonutils.result.ResultCode;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import com.liuscoding.ucenter.entity.Member;
import com.liuscoding.ucenter.mapper.MemberMapper;
import com.liuscoding.ucenter.model.form.MemberForm;
import com.liuscoding.ucenter.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-16
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    /**
     * 会员登录
     *
     * @param memberForm 会员表单
     * @return token
     */
    @Override
    public String login(MemberForm memberForm) {
        //获取手机和密码
        String mobile = memberForm.getMobile();
        String password = memberForm.getPassword();

        if (StringUtils.isBlank(mobile) || StringUtils.isBlank(password)){
            throw GuliException.from(ResultCode.LOGIN_ERROR);
        }


        //判断手机号是否正确
        LambdaQueryWrapper<Member> memberQuery = new LambdaQueryWrapper<>();
        memberQuery.eq(Member::getMobile,mobile);

        Member member = this.getOne(memberQuery);

        //判断对象是否为空
        member = Optional.ofNullable(member).orElseThrow(() -> GuliException.from(ResultCode.LOGIN_ERROR));


        //判断密码
        //因为存储到数据库密码肯定加密的
        //把输入的密码进行加密，再和数据密码进行比较
        //加密方式 MD5
        if(password.equalsIgnoreCase(DigestUtils.md5DigestAsHex(member.getPassword().getBytes(StandardCharsets.UTF_8)))){
            throw GuliException.from(ResultCode.LOGIN_ERROR);
        }

        //判断用户是否禁用
        if (member.getIsDisabled()){
            throw GuliException.from(ResultCode.LOGIN_ERROR);
        }

        //登录成功
        //生成token字符串，使用jwt字符串
        String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return jwtToken;
    }
}
