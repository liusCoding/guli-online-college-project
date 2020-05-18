package com.liuscoding.order.service;

import com.liuscoding.order.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-18
 */
public interface PayLogService extends IService<PayLog> {

    /**
     * 根据订单号生成微信二维码
     * @param orderNo 订单号
     * @return 二维码地址及其他信息
     */
    Map<String, Object> createWxNativePay(String orderNo);

    /**
     * 根据订单号查询订单支付状态
     * @param orderNo 订单号
     * @return map 包括订单号，支付状态等
     */
    Map<String, String> queryPayStatus(String orderNo);

    /**
     * 支付成功，更新订单表支付状态
     * @param map
     */
    void updateOrderStatus(Map<String, String> map);
}
