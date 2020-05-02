package com.liuscoding.edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuscoding.commonutils.vo.ResultVo;
import com.liuscoding.edu.entity.Teacher;
import com.liuscoding.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResultVo findAll(){
        List<Teacher> teacherList = teacherService.list(null);
        return ResultVo.ok().data("items",teacherList);
    }


    /**
     * 2. 逻辑删除讲师的方法
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("逻辑删除讲师")
    public ResultVo deleteTeacher(@ApiParam(name = "id",required = true) @PathVariable String id){
        boolean result = teacherService.removeById(id);
        if(result){
           return ResultVo.ok();
        }else {
           return ResultVo.error();
        }
    }

    @ApiOperation("分页查询讲师（不带条件）")
    @GetMapping("/pageTeacher/{page}/{size}")
    public ResultVo pageTeacher(@ApiParam(value = "page",defaultValue = "1") @PathVariable("page") int page,
                                @ApiParam(value = "size",defaultValue = "10")@PathVariable("size") int size){

        //1.创建page对象
        IPage<Teacher> teacherPage = new Page<>(page, size);
        teacherPage = teacherService.page(teacherPage, null);

        Map<String,Object> map = new HashMap<>(16);

        map.put("total",teacherPage.getTotal());
        map.put("rows",teacherPage.getRecords());

        return ResultVo.ok().data(map);
    }
}

