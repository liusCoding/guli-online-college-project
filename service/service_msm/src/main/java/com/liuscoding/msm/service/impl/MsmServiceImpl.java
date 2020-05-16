package com.liuscoding.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.liuscoding.msm.service.MsmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @className: MsmServiceImpl
 * @description: 短信业务层实现
 * @author: liusCoding
 * @create: 2020-05-16 11:04
 */
@Service
public class MsmServiceImpl implements MsmService {

    /**
     * 发送验证码
     *
     * @param param 验证码
     * @param phone 手机号
     * @return 发送结果
     */
    @Override
    public boolean send(Map<String, Object> param, String phone) {

//        keyId: LTAI4GLDi5URc75mnamqeXmQ
//        keySecret: 5CKsFn3LmqojuNB5ly5GC38mZIjGDT


        if (StringUtils.isBlank(phone)){
            return false;
        }
        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI4GLDi5URc75mnamqeXmQ", "5CKsFn3LmqojuNB5ly5GC38mZIjGDT");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定的参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");


        //设置发送相关的参数
        //手机号
        request.putQueryParameter("PhoneNumbers",phone);
        //申请阿里云 签名名称
        request.putQueryParameter("SignName","我的快易销在线购物网站");
        //申请阿里云 模板code
        request.putQueryParameter("TemplateCode","SMS_190277104");
        //验证码数据，转换json数据传递
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        try {
            //最终发送
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }


    }
}
