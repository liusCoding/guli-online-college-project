package com.liuscoding.statistics.client;

import com.liuscoding.commonutils.result.ResultCode;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import org.springframework.stereotype.Component;

/**
 * @className: MemberClientImpl
 * @description:
 * @author: liusCoding
 * @create: 2020-05-19 06:49
 */
@Component
public class MemberClientImpl implements MemberClient {
    /**
     * 查询指定日期的注册人数
     *
     * @param day 指定日期
     * @return 注册人数
     */
    @Override
    public Integer countRegister(String day) {
        throw GuliException.from(ResultCode.REMOTE_CALL_MEMBER);
    }
}
