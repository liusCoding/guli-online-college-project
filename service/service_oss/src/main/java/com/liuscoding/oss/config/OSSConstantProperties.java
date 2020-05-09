package com.liuscoding.oss.config;

import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @className: OSSConfig
 * @description:
 * @author: liusCoding
 * @create: 2020-05-09 15:12
 */
@Component
@ConfigurationProperties("aliyun.oss.file")
@Setter //必须加Set方法
public class OSSConstantProperties implements InitializingBean {
    /**
     * 存储节点地址
     */
    private String endpoint;

    /**
     * 秘钥id
     */
    private String keyid;

    /**
     * 密匙
     */
    private String keysecret;

    /**
     * 文件夹名称
     */
    private String bucketname;


    public static String END_POINT;

    public static String KEY_ID;

    public static String KEY_SECRET;

    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        KEY_ID = keyid;
        KEY_SECRET = keysecret;
        BUCKET_NAME = bucketname;
    }
}
