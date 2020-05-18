package com.liuscoding.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.common.collect.Maps;
import com.liuscoding.commonutils.result.ResultCode;
import com.liuscoding.order.entity.Order;
import com.liuscoding.order.entity.PayLog;
import com.liuscoding.order.mapper.PayLogMapper;
import com.liuscoding.order.service.OrderService;
import com.liuscoding.order.service.PayLogService;
import com.liuscoding.order.utils.HttpClient;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-18
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    private final OrderService orderService;

    public PayLogServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 根据订单号生成微信二维码
     *
     * @param orderNo 订单号
     * @return 二维码地址及其他信息
     */
    @Override
    public Map<String, Object> createWxNativePay(String orderNo) {

        //1.根据订单号查询订单信息
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getOrderNo,orderNo);
        Order order = orderService.getOne(queryWrapper);


        //2.使用map 生成微信二维码需要的参数
        Map<String,String> map = Maps.newHashMap();

        map.put("appid","wx74862e0dfcf69954");
        map.put("mch_id", "1558950191");
        map.put("nonce_str", WXPayUtil.generateNonceStr());
        //课程标题
        map.put("body", order.getCourseTitle());
        //订单号
        map.put("out_trade_no", orderNo);
        map.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
        map.put("spbill_create_ip", "127.0.0.1");
        map.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");
        map.put("trade_type", "NATIVE");


        //3.发送httpclient请求，传递参数xml格式，微信支付提供的固定的地址

        HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");


        try {
            //设置xml格式的参数
            client.setXmlParam(WXPayUtil.generateSignedXml(map,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);

            //执行post请求发送
            client.post();

            //4.得到发送请求返回的内容
            //返回内容 是使用xml格式返回
            String xml = client.getContent();


            //把xml格式转换成map集合，把map集合返回
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);

            //最终返回数据 的封装
            Map dataMap = Maps.newHashMap();
            dataMap.put("out_trade_no", orderNo);
            dataMap.put("course_id", order.getCourseId());
            dataMap.put("total_fee", order.getTotalFee());
            //返回二维码操作状态码
            dataMap.put("result_code", resultMap.get("result_code"));
            //二维码地址
            dataMap.put("code_url", resultMap.get("code_url"));

            return dataMap;

        } catch (Exception e) {
            e.printStackTrace();
            throw GuliException.from(ResultCode.WXPAY_FAIL);
        }
    }

    /**
     * 根据订单号查询订单支付状态
     *
     * @param orderNo 订单号
     * @return map 包括订单号，支付状态等
     */
    @Override
    public Map<String, String> queryPayStatus(String orderNo) {

        //1、封装参数
        Map m = new HashMap<>(16);
        m.put("appid", "wx74862e0dfcf69954");
        m.put("mch_id", "1558950191");
        m.put("out_trade_no", orderNo);
        m.put("nonce_str", WXPayUtil.generateNonceStr());


        //2.发送httpClient
        HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
        try {
            client.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();

            //3.得到请求返回的内容
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);

            //4.转成map返回
            return resultMap;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 支付成功，更新订单表支付状态
     *
     * @param map
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrderStatus(Map<String, String> map) {

        //从map中获取订单号
        String orderNo = map.get("out_trade_no");

        //根据订单号查询订单信息
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getOrderNo,orderNo);
        Order order = orderService.getOne(queryWrapper);

        if(order.getStatus().intValue() == 1){
            return ;
        }

        //1代表已经支付
        order.setStatus(1);
        orderService.updateById(order);

        //向支付表添加支付记录
        PayLog payLog = PayLog.builder()
                .orderNo(orderNo)
                .payTime(new Date())
                .payType(1)
                .totalFee(order.getTotalFee())
                .tradeState(map.get("trade_state"))
                .transactionId(map.get("transaction_id"))
                .attr(JSON.toJSONString(map))
                .build();
        this.save(payLog);
    }
}
