package com.liuscoding.msm.service;

import java.util.Map;

public interface MsmService {

    /**
     * 发送验证码
     * @param param 验证码
     * @param phone 手机号
     * @return 发送结果
     */
    boolean send(Map<String, Object> param, String phone);
}
