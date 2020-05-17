package com.liuscoding.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuscoding.commonutils.vo.ResultVo;
import com.liuscoding.edu.entity.Course;
import com.liuscoding.edu.entity.Teacher;
import com.liuscoding.edu.service.CourseService;
import com.liuscoding.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @className: TeacherFrontController
 * @description: 老师C端Controller
 * @author: liusCoding
 * @create: 2020-05-17 07:51
 */

@RestController
@RequestMapping("/edu/teacherFront")
@CrossOrigin
public class TeacherFrontController {

    private final TeacherService teacherService;
    private final CourseService courseService;

    public TeacherFrontController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }


    @ApiOperation("讲师C端分页查询")
    @GetMapping("/getTeacherInfoList/{page}/{size}")
    public ResultVo getTeacherFrontList(@PathVariable long page ,@PathVariable long size){

        Page<Teacher> teacherPage = new Page<>(page,size);
        teacherPage = teacherService.getTeacherFrontList(teacherPage);

        return ResultVo.ok().data("page",teacherPage);
    }

    @ApiOperation("讲师详情的功能")
    @GetMapping("/getTeacherFrontInfo/{teacherId}")
    public ResultVo getTeacherFront(@PathVariable String teacherId){

        //1.根据讲师id查询讲师的基本信息
        Teacher teacher = teacherService.getById(teacherId);

        //2.根据讲师查询所讲课程
        LambdaQueryWrapper<Course> courseQuery = new LambdaQueryWrapper<>();
        courseQuery.eq(Course::getTeacherId,teacherId);
        List<Course> courseList = courseService.list(courseQuery);
        return ResultVo.ok().data("teacher",teacher).data("courseList",courseList);
    }
}
