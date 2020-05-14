package com.liuscoding.edu.controller;


import com.liuscoding.commonutils.vo.ResultVo;
import com.liuscoding.edu.entity.Video;
import com.liuscoding.edu.enums.EduResultCode;
import com.liuscoding.edu.model.form.VideoForm;
import com.liuscoding.edu.service.VideoService;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@CrossOrigin
@Api(tags = "课程视频管理")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
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
    public ResultVo deleteVideo(@PathVariable  String id){
        //TODO  删除小节的时候，同时把里面的视频删除
        videoService.removeById(id);
        return ResultVo.ok();
    }
}

