package com.liuscoding.vod.constants;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @className: VodConstants
 * @description:
 * @author: liusCoding
 * @create: 2020-05-14 14:41
 */
@Data
@Component
@ConfigurationProperties("aliyun.vod.file")
public class VodConstants implements InitializingBean {

    private String keyId;

    private String keySecret;

    public static String ACCESS_KEY_SECRET;

    public static String ACCESS_KEY_ID;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;

        ACCESS_KEY_SECRET = keySecret;
    }
}
