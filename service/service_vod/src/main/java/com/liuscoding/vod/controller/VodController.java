package com.liuscoding.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.liuscoding.commonutils.result.ResultCode;
import com.liuscoding.commonutils.vo.ResultVo;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import com.liuscoding.vod.constants.InitAliyunClient;
import com.liuscoding.vod.constants.VodConstants;
import com.liuscoding.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * @className: VodController
 * @description:
 * @author: liusCoding
 * @create: 2020-05-14 14:45
 */

@Api(tags = "阿里云视频服务")
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    private final VodService vodService;

    public VodController(VodService vodService) {
        this.vodService = vodService;
    }

    @ApiOperation("上传视频到阿里云")
    @PostMapping("/uploadAliyunVideo")
    public ResultVo uploadAliyunVideo(MultipartFile file) throws IOException {
        Optional.ofNullable(file).orElseThrow(()-> GuliException.from(ResultCode.FILE_EMPTY));
        String videoId = vodService.uploadVideoToAliyun(file);
        return ResultVo.ok().data("videoId",videoId);
    }


    @ApiOperation("根据视频id删除阿里云视频")
    @DeleteMapping("/removeAliyunVideo/{id}")
    public ResultVo removeAliyunVideo(@PathVariable String id){
        //初始化阿里云客户端对象
        DefaultAcsClient client = InitAliyunClient.initVodClient(VodConstants.ACCESS_KEY_ID, VodConstants.ACCESS_KEY_SECRET);

        //创建删除视频的request对象
        DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
        deleteVideoRequest.setVideoIds(id);
        try {
            client.getAcsResponse(deleteVideoRequest);
        } catch (ClientException e) {
            e.printStackTrace();
            throw GuliException.from(ResultCode.DELETE_ERROR);
        }

        return ResultVo.ok();
    }
}
