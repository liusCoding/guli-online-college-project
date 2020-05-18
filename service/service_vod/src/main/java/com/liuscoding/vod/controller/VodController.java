package com.liuscoding.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
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
import java.util.List;
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

    @ApiOperation("删除多个阿里云视频")
    @DeleteMapping("/deleteBatch")
    public ResultVo deleteBatch(@RequestParam("videoIdList") List<String> videoIdList){
        vodService.deleteBatch(videoIdList);
        return ResultVo.ok();
    }

    @ApiOperation("根据视频id获取视频凭证")
    @GetMapping("/getPlayAuth/{id}")
    public ResultVo getPlayAuth(@PathVariable String id ){

        //创建初始化对象
        DefaultAcsClient client = InitAliyunClient.initVodClient(VodConstants.ACCESS_KEY_ID, VodConstants.ACCESS_KEY_SECRET);

        //创建获取凭证的request和response对象
        GetVideoPlayAuthRequest authRequest = new GetVideoPlayAuthRequest();

        //向authRequest设置视频id
        authRequest.setVideoId(id);

        //调用方法得到凭证
        GetVideoPlayAuthResponse acsResponse = null;
        try {
            acsResponse = client.getAcsResponse(authRequest);
        } catch (ClientException e) {
            e.printStackTrace();
            throw GuliException.from(ResultCode.GET_PLAYAUTH_FAIL);
        }
        String playAuth = acsResponse.getPlayAuth();

        return ResultVo.ok().data("playAuth",playAuth);
    }
}
