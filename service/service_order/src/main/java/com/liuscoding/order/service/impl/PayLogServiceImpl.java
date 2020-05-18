package com.liuscoding.order.service.impl;

import com.liuscoding.order.entity.PayLog;
import com.liuscoding.order.mapper.PayLogMapper;
import com.liuscoding.order.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
