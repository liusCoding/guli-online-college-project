package com.liuscoding.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuscoding.commonutils.vo.ResultVo;
import com.liuscoding.edu.entity.Course;
import com.liuscoding.edu.entity.Teacher;
import com.liuscoding.edu.service.CourseService;
import com.liuscoding.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: IndexFrontController
 * @description: 首页数据
 * @author: liusCoding
 * @create: 2020-05-15 22:28
 */

@Api(tags = "首页数据管理")
@RequestMapping("/edu/indexFront")
@RestController
@CrossOrigin
public class IndexFrontController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    public IndexFrontController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }


    /**
     * 查询前8条热门课程  查询前4名讲师
     * @return
     */
    @GetMapping("/index")
    @ApiOperation("首页数据查询")
    public ResultVo index(){
        //查询前8条热门课程
        LambdaQueryWrapper<Course> courseQuery = new LambdaQueryWrapper<>();
        courseQuery.orderByDesc(Course::getId);
        courseQuery.last("limit 8");

        List<Course> courseList = courseService.list(courseQuery);

        //查询前4条名师
        LambdaQueryWrapper<Teacher> teacherQuery = new LambdaQueryWrapper<>();
        teacherQuery.orderByDesc(Teacher::getId);
        teacherQuery.last("limit 4");

        List<Teacher> teacherList = teacherService.list(teacherQuery);

        return ResultVo.ok().data("courseList",courseList).data("teacherList",teacherList);

    }
}
