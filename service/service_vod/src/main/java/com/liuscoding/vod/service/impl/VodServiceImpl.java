package com.liuscoding.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.liuscoding.vod.constants.VodConstants;
import com.liuscoding.vod.service.VodService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @className: VodServiceImpl
 * @description:
 * @author: liusCoding
 * @create: 2020-05-14 14:49
 */
@Service
public class VodServiceImpl implements VodService {
    /**
     * 上传视频到阿里云
     *
     * @param file 视频文件
     * @return 视频id
     */
    @Override
    public String uploadVideoToAliyun(MultipartFile file) throws IOException {

        //上传视频原始名称
        String originalFilename = file.getOriginalFilename();
        //上传之后显示的名称
        String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

        String videoId =null;

        //获取文件的输入流

        InputStream inputStream = file.getInputStream();
        UploadStreamRequest request = new UploadStreamRequest(VodConstants.ACCESS_KEY_ID, VodConstants.ACCESS_KEY_SECRET, title, originalFilename, inputStream);
        UploadVideoImpl uploadVideo = new UploadVideoImpl();
        UploadStreamResponse response = uploadVideo.uploadStream(request);
        videoId = response.getVideoId();


        return videoId;
    }
}
