package com.liuscoding.edu.controller;


import com.liuscoding.edu.entity.Teacher;
import com.liuscoding.edu.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-02
 */
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {

    /**
     * 注入teacherService
     */
    private final TeacherService teacherService;
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * 1.查询讲师表的所有数据
     * @return List<Teacher>
     */
    @GetMapping("/findAll")
    public List<Teacher> findAll(){
        List<Teacher> teacherList = teacherService.list(null);
        return teacherList;
    }

}

