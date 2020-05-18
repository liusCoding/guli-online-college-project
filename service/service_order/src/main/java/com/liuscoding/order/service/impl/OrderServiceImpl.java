package com.liuscoding.order.service.impl;

import com.liuscoding.order.entity.Order;
import com.liuscoding.order.mapper.OrderMapper;
import com.liuscoding.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-18
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
