package com.liuscoding.ucenter.service.impl;

import com.liuscoding.ucenter.entity.Member;
import com.liuscoding.ucenter.mapper.MemberMapper;
import com.liuscoding.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
