package com.liuscoding.edu.service.impl;

import com.liuscoding.edu.entity.Course;
import com.liuscoding.edu.mapper.CourseMapper;
import com.liuscoding.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-10
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}
