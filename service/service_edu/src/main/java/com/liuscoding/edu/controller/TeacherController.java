package com.liuscoding.edu.controller;


import com.liuscoding.edu.entity.Teacher;
import com.liuscoding.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "讲师管理")
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
    @ApiOperation("查询所有讲师列表")
    @GetMapping("/findAll")
    public List<Teacher> findAll(){
        List<Teacher> teacherList = teacherService.list(null);
        return teacherList;
    }


    /**
     * 2. 逻辑删除讲师的方法
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("逻辑删除讲师")
    public boolean deleteTeacher(@ApiParam(name = "id",required = true) @PathVariable String id){
        boolean result = teacherService.removeById(id);
        return result;
    }
}

