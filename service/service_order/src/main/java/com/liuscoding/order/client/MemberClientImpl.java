package com.liuscoding.order.client;

import com.liuscoding.commonutils.model.vo.MemberOrderVo;
import com.liuscoding.commonutils.result.ResultCode;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import org.springframework.stereotype.Component;

/**
 * @className: MemberClientImpl
 * @description:
 * @author: liusCoding
 * @create: 2020-05-18 13:48
 */
@Component
public class MemberClientImpl implements MemberClient {
    /**
     * 根据会员id  查询会员信息
     *
     * @param id 会员id
     * @return MemberOrderVo 会员信息
     */
    @Override
    public MemberOrderVo getMemberInfoOrderById(String id) {
       throw GuliException.from(ResultCode.REMOTE_CALL_MEMBER);
    }
}
