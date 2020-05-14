package com.liuscoding.edu.service;

import com.liuscoding.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-10
 */
public interface VideoService extends IService<Video> {

    /**
     * 根据课程id删除视频
     * @param courseId
     */
    void deleteVideoByCourseId(String courseId);
}
