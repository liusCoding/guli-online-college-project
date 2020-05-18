package com.liuscoding.edu.client;

import com.liuscoding.commonutils.result.ResultCode;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import org.springframework.stereotype.Component;

/**
 * @className: OrdersClientImpl
 * @description:
 * @author: liusCoding
 * @create: 2020-05-18 17:48
 */
@Component
public class OrdersClientImpl implements OrdersClient {
    /**
     * 根据课程id和用户id查询订单表中订单状态
     *
     * @param courseId 课程id
     * @param memberId 会员id
     * @return 是否购买
     */
    @Override
    public Boolean isBuyCourse(String courseId, String memberId) {
        throw GuliException.from(ResultCode.ERROR);
    }
}
