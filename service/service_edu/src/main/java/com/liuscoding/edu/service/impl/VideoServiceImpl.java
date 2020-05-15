package com.liuscoding.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuscoding.edu.client.VodClient;
import com.liuscoding.edu.entity.Video;
import com.liuscoding.edu.enums.EduResultCode;
import com.liuscoding.edu.mapper.VideoMapper;
import com.liuscoding.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-10
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    private final VodClient vodClient;

    public VideoServiceImpl(VodClient vodClient) {
        this.vodClient = vodClient;
    }

    /**
     * 根据课程id删除视频
     *
     * @param courseId
     */
    @Override
    public void deleteVideoByCourseId(String courseId) {
        LambdaQueryWrapper<Video> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Video::getCourseId,courseId);
        queryWrapper.select(Video::getVideoSourceId);
        List<Video> videoList = this.list(queryWrapper);

        List<String> videoSourceIdList = videoList.stream()
                .map(Video::getVideoSourceId)
                .collect(toList());

        //删除阿里云多个视频
        if(!CollectionUtils.isEmpty(videoSourceIdList)){
            vodClient.deleteBatch(videoSourceIdList);
        }

        boolean removeResult = this.remove(queryWrapper);
        if(!removeResult){
            throw GuliException.from(EduResultCode.DELETE_ERROR);
        }
    }
}
