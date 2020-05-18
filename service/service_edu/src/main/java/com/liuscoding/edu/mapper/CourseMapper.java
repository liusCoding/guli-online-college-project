package com.liuscoding.edu.mapper;

import com.liuscoding.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuscoding.edu.model.vo.CoursePublishVo;
import com.liuscoding.edu.model.vo.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-10
 */
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 根据课程id  查询课程信息
     * @param courseId 课程id
     * @return CoursePublishVo
     */
     CoursePublishVo getPublishCourseInfo(String courseId);

    /**
     * 根据课程id，查询课程的基本信息
     * @param courseId
     * @return
     */
    CourseWebVo getBaseCourseInfo(String courseId);
}
