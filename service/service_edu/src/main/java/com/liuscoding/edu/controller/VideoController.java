package com.liuscoding.edu.controller;


import com.liuscoding.commonutils.result.ResultCode;
import com.liuscoding.commonutils.vo.ResultVo;
import com.liuscoding.edu.client.VodClient;
import com.liuscoding.edu.entity.Video;
import com.liuscoding.edu.enums.EduResultCode;
import com.liuscoding.edu.model.form.VideoForm;
import com.liuscoding.edu.service.VideoService;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-10
 */
@RestController
@RequestMapping("/edu/video")
//@CrossOrigin
@Api(tags = "课程视频管理")
public class VideoController {

    private final VideoService videoService;

    private final VodClient vodClient;

    public VideoController(VideoService videoService, VodClient vodClient) {
        this.videoService = videoService;
        this.vodClient = vodClient;
    }

    @ApiOperation("添加课程视频")
    @PostMapping("/addVideo")
    public ResultVo addVideo(@RequestBody VideoForm videoForm){
        Video video = new Video();
        BeanUtils.copyProperties(videoForm,video);

        boolean saveResult = videoService.save(video);
        if (!saveResult){
            throw GuliException.from(EduResultCode.SAVE_ERROR);
        }
        return  ResultVo.ok();
    }

    @ApiOperation("更新小节")
    @PutMapping("/updateVideo")
    public ResultVo updateVideo(@RequestBody VideoForm videoForm){
        Video video = new Video();
        BeanUtils.copyProperties(videoForm,video);

        boolean updateResult = videoService.updateById(video);
        if (!updateResult){
            throw GuliException.from(EduResultCode.UPDATE_ERROR);
        }
        return  ResultVo.ok();
    }

    @ApiOperation("根据id查询小节")
    @GetMapping("/{id}")
    public ResultVo getVideoById(@PathVariable String id){
        Video video = videoService.getById(id);
        return ResultVo.ok().data("video",video);
    }

    @ApiOperation("根据id删除视频")
    @DeleteMapping("/{id}")
    /**
     * 删除小节，同时删除对应的阿里云视频
     */
    public ResultVo deleteVideo(@PathVariable  String id){

        //1.根据小节id获取视频id，调用方法实现删除
        Video video = videoService.getById(id);
        String videoSourceId = video.getVideoSourceId();

        if(StringUtils.isNotBlank(videoSourceId)){
            //根据视频id  远程调用实现视频删除
            ResultVo resultVo = vodClient.removeAliyunVideo(videoSourceId);
            if(resultVo.getCode().equals(ResultCode.ERROR.getCode())){
                throw GuliException.from(ResultCode.ERROR);
            }
        }

        //2.删除小节
        videoService.removeById(id);
        return ResultVo.ok();
    }
}

