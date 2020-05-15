package com.liuscoding.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 阿里云视频上传服务
 *
 * @author  liuscoding
 */
public interface VodService {

    /**
     * 上传视频到阿里云
     * @param file  视频文件
     * @return 视频id
     * @throws IOException IO异常
     */
    String uploadVideoToAliyun(MultipartFile file) throws IOException;

    /**
     * 批量删除阿里云视频
     * @param videoIdList 多个视频id
     */
    void deleteBatch(List<String> videoIdList);
}
