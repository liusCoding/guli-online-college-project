package com.liuscoding.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 阿里云OSS服务
 * @author liuscoding
 */
public interface IOssService {

    /**
     * 上传头像到阿里云OSS
     * @param file 头像
     * @return  头像url
     */
    String uploadFileAvatar(MultipartFile file);
}
