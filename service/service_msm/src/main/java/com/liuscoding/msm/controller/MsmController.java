package com.liuscoding.msm.controller;

import com.liuscoding.commonutils.result.ResultCode;
import com.liuscoding.commonutils.vo.ResultVo;
import com.liuscoding.msm.service.MsmService;
import com.liuscoding.msm.utils.RandomUtil;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @className: MsmController
 * @description: 短信Controller
 * @author: liusCoding
 * @create: 2020-05-16 11:03
 */

@RestController
@RequestMapping("/msm")
@Api(tags = "短信服务")
@CrossOrigin
public class MsmController {
    private final MsmService msmService;
    private final StringRedisTemplate redisTemplate;

    public MsmController(MsmService msmService, StringRedisTemplate redisTemplate) {
        this.msmService = msmService;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/send/{phone}")
    @ApiOperation("发送验证码")
    public ResultVo sendMsm(@PathVariable("phone") String phone){

        //从redis获取验证码，如果能获取的到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if (StringUtils.isNotBlank(code)){
           return ResultVo.ok().data("code",code);
        }
        //生成随机数，传递给阿里云进行发送
        code = RandomUtil.getFourBitRandom();

        Map<String,Object> param = new HashMap<>(16);
        param.put("code", code);

        boolean isSend = msmService.send(param,phone);
        if(!isSend){
            throw  GuliException.from(ResultCode.VALID_CODE_SEND_FAIL);
        }
        //发送成功，把发送的验证码放到redis里面
        redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
        return ResultVo.ok().data("code",code);
    }
}
