package com.liuscoding.edu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @className: OrdersClient
 * @description:
 * @author: liusCoding
 * @create: 2020-05-18 17:47
 */

@FeignClient(value = "service-order",fallback = OrdersClientImpl.class)
@Component
public interface OrdersClient {

    /**
     * 根据课程id和用户id查询订单表中订单状态
     * @param courseId 课程id
     * @param memberId 会员id
     * @return 是否购买
     */
    @GetMapping("/order/isBuyCourse/{courseId}/{memberId}")
     Boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
