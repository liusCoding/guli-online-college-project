package com.liuscoding.ucenter.service;

import com.liuscoding.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liuscoding.ucenter.model.form.MemberForm;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-16
 */
public interface MemberService extends IService<Member> {

    /**
     * 会员登录
     * @param memberForm 会员表单
     * @return token
     */
    String login(MemberForm memberForm);
}
