package com.liuscoding.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.liuscoding.commonutils.result.ResultCode;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import com.liuscoding.vod.constants.InitAliyunClient;
import com.liuscoding.vod.constants.VodConstants;
import com.liuscoding.vod.service.VodService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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

    /**
     * 批量删除阿里云视频
     *
     * @param videoIdList 多个视频id
     */
    @Override
    public void deleteBatch(List<String> videoIdList) {
        try {
            //初始化对象
            DefaultAcsClient client = InitAliyunClient.initVodClient(VodConstants.ACCESS_KEY_ID, VodConstants.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //videoIdList值转换成 1,2,3
            String videoIds = StringUtils.join(videoIdList.toArray(), ",");

            //向request设置视频id
            request.setVideoIds(videoIds);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        }catch(Exception e) {
            e.printStackTrace();
            throw GuliException.from(ResultCode.DELETE_ERROR);
        }
    }

//    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("11");
//        list.add("22");
//        list.add("33");
//        // 11,22,33
//       // String join = StringUtils.join(list.toArray(), ",");
//
//        String join = list.stream().collect(Collectors.joining(","));
//        System.out.println(join);
//    }
}
