package com.liuscoding.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.liuscoding.oss.config.OSSConstantProperties;
import com.liuscoding.oss.service.IOssService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @className: OssServiceImpl
 * @description: 阿里云OSS服务实现
 * @author: liusCoding
 * @create: 2020-05-09 15:57
 */

@Service
public class OssServiceImpl implements IOssService {
    /**
     * 上传头像到阿里云OSS
     *
     * @param file 头像
     * @return 头像url
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) {

        String endPoint = OSSConstantProperties.END_POINT;
        String keyId = OSSConstantProperties.KEY_ID;
        String keySecret = OSSConstantProperties.KEY_SECRET;
        String bucketName = OSSConstantProperties.BUCKET_NAME;

        OSS ossClient = new OSSClientBuilder().build(endPoint, keyId, keySecret);


        try {
            //获取上传的文件输入流
            InputStream inputStream = file.getInputStream();

            //获取文件名称
            String filename = file.getOriginalFilename();

            //1.在文件名称里面添加唯一的值
            String uuid = UUID.randomUUID().toString().replace("-", "");

            //阿里云oss的文件名称
            filename = uuid+filename;

            //2 把文件按照日期进行分类
            //获取当前日期
            //   2020/5/12
            String datePath = new DateTime().toString("yyyy/MM/dd");

            //拼接
            filename = datePath+"/"+filename;

            //调用oss方法实现上传
            //第一个参数   Bucket名称
            //第二个参数   上传到oss文件路径和文件名称  aa/bb/1.jpg
            //第三个参数    上传文件输入流

            ossClient.putObject(bucketName,filename,inputStream);

            //关闭OSSClient
            ossClient.shutdown();

            //上传之后拼接图片的路径
            String url = new  StringBuilder("https://").append(bucketName).append(".")
                    .append(endPoint).append("/").append(filename).toString();

            return url;

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }
}
