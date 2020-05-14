package com.liuscoding.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuscoding.edu.entity.Video;
import com.liuscoding.edu.enums.EduResultCode;
import com.liuscoding.edu.mapper.VideoMapper;
import com.liuscoding.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import org.springframework.stereotype.Service;

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

    /**
     * 根据课程id删除视频
     *
     * @param courseId
     */
    @Override
    public void deleteVideoByCourseId(String courseId) {
        LambdaQueryWrapper<Video> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Video::getCourseId,courseId);
        boolean removeResult = this.remove(queryWrapper);
        if(!removeResult){
            throw GuliException.from(EduResultCode.DELETE_ERROR);
        }
    }
}
