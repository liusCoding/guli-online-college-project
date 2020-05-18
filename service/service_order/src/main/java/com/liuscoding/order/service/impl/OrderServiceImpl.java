package com.liuscoding.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuscoding.commonutils.model.vo.CourseOrderVo;
import com.liuscoding.commonutils.model.vo.MemberOrderVo;
import com.liuscoding.order.client.CourseClient;
import com.liuscoding.order.client.MemberClient;
import com.liuscoding.order.entity.Order;
import com.liuscoding.order.mapper.OrderMapper;
import com.liuscoding.order.service.OrderService;
import com.liuscoding.order.utils.OrderNoUtil;
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

    private final CourseClient courseClient;

    private final MemberClient memberClient;

    public OrderServiceImpl(CourseClient courseClient, MemberClient memberClient) {
        this.courseClient = courseClient;
        this.memberClient = memberClient;
    }

    /**
     * 创建订单
     *
     * @param courseId 课程号
     * @param memberId 会员Id
     * @return 订单号
     */
    @Override
    public String createOrder(String courseId, String memberId) {

        //通过远程调用根据会员id获取会员信息
        MemberOrderVo memberOrderVo = memberClient.getMemberInfoOrderById(memberId);

        //通过远程调用根据课程id获取课程信息
        CourseOrderVo courseInfoOrder = courseClient.getCourseInfoOrder(courseId);

        //创建order 对象 向order对象填充数据

        Order order = Order.builder()
                .orderNo(OrderNoUtil.getOrderNo())
                .courseId(courseId)
                .courseCover(courseInfoOrder.getCover())
                .courseTitle(courseInfoOrder.getTitle())
                .memberId(memberId)
                .nickname(memberOrderVo.getNickname())
                .mobile(memberOrderVo.getMobile())
                .teacherName(courseInfoOrder.getTeacherName())
                .totalFee(courseInfoOrder.getPrice())
                //订单状态 （0：未支付，1：已支付）
                .status(0)
                //支付方式 1.微信
                .payType(1)
                .build();

        this.save(order);
        return order.getOrderNo();
    }
}
