package com.liuscoding.order.controller;


import com.liuscoding.commonutils.result.ResultCode;
import com.liuscoding.commonutils.vo.ResultVo;
import com.liuscoding.order.service.PayLogService;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-18
 */
@RestController
@RequestMapping("/order/pay")
@CrossOrigin
public class PayLogController {
    private final PayLogService payLogService;

    public PayLogController(PayLogService payLogService) {
        this.payLogService = payLogService;
    }

    @ApiOperation("生成微信二维码的接口")
    @GetMapping("/createNative/{orderNo}")
    public ResultVo createWxNativePay(@PathVariable String orderNo){
        //返回的信息包括二维码地址，还需要其他的信息

        Map<String,Object> map = payLogService.createWxNativePay(orderNo);
        return ResultVo.ok().data(map);
    }

    @ApiOperation("查询订单的支付状态")
    @GetMapping("/queryPayStatus/{orderNo}")
    public ResultVo queryPayStatus(@PathVariable String orderNo){
        Optional.ofNullable(orderNo).orElseThrow(()-> GuliException.from(ResultCode.PARAMS_ERROR));
       Map<String,String> map =  payLogService.queryPayStatus(orderNo);

       if(CollectionUtils.isEmpty(map)){
           return ResultVo.error().message("支付出错了");
       }

       //如果返回的map不为空，通过map获取订单内容
       if("SUCCESS".equals(map.get("trade_state"))){
           //添加记录到支付表，更新订单表订单状态
           payLogService.updateOrderStatus(map);
           return ResultVo.ok().message("支付成功");
       }
        return ResultVo.ok().code(25000).message("支付中");
    }

}

