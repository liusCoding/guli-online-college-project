package com.liuscoding.ucenter.mapper;

import com.liuscoding.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-16
 */
public interface MemberMapper extends BaseMapper<Member> {

    /**
     * 查询指定day的注册人数
     * @param day 指定日期
     * @return 注册日期
     */
    Integer countRegisterDay(String day);
}
