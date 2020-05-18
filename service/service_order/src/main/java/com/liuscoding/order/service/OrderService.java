package com.liuscoding.order.service;

import com.liuscoding.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-18
 */
public interface OrderService extends IService<Order> {

    /**
     * 创建订单
     * @param courseId 课程号
     * @param memberId 会员Id
     * @return  订单号
     */
    String createOrder(String courseId, String memberId);
}
