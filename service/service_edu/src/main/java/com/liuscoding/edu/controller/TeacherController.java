package com.liuscoding.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuscoding.commonutils.vo.ResultVo;
import com.liuscoding.edu.entity.Teacher;
import com.liuscoding.edu.model.query.TeacherQuery;
import com.liuscoding.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
//@CrossOrigin//解决跨域
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
        //调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        IPage<Teacher> teacherPage = new Page<>(page, size);
        teacherService.page(teacherPage, null);

        Map<String,Object> map = new HashMap<>(16);

        map.put("total",teacherPage.getTotal());
        map.put("rows",teacherPage.getRecords());

        return ResultVo.ok().data(map);
    }


    /**
     * 教师分页带条件查询
     * @param page 当前页
     * @param size  每页的数量
     * @return   ResultVo
     */

    @ApiOperation("讲师分页查询带条件")
    @PostMapping("/pageTeacherCondition/{page}/{size}")
    public ResultVo pageTeacherCondition(@ApiParam(value = "page",defaultValue = "1") @PathVariable("page") int page,
                                         @ApiParam(value = "size",defaultValue = "10")@PathVariable("size") int size,
                                         @RequestBody TeacherQuery teacherQuery){
        //创建page对象
        IPage<Teacher> pageTeacher = new Page<>(page,size);

        //构建条件
        LambdaQueryWrapper<Teacher> query = new LambdaQueryWrapper<>();
        query.like(StringUtils.isNotBlank(teacherQuery.getName()),Teacher::getName,teacherQuery.getName());
        query.eq(Objects.nonNull(teacherQuery.getLevel()),Teacher::getLevel,teacherQuery.getLevel());
        query.ge(StringUtils.isNotBlank(teacherQuery.getBegin()),Teacher::getGmtCreate,teacherQuery.getBegin());
        query.le(StringUtils.isNotBlank(teacherQuery.getEnd()),Teacher::getGmtCreate,teacherQuery.getEnd());
        query.orderByDesc(Teacher::getGmtCreate);
        teacherService.page(pageTeacher, query);
        Map<String,Object> map = new HashMap<>(16);
        map.put("total",pageTeacher.getTotal());
        map.put("rows",pageTeacher.getRecords());
        return ResultVo.ok().data(map);
    }

    @GetMapping("/getTeacher/{id}")
    @ApiOperation("根据讲师id进行查询")
    public ResultVo getTeacher(@ApiParam(value = "id",required = true) @PathVariable("id") String id){
        Teacher teacher = teacherService.getById(id);
        return ResultVo.ok().data("teacher",teacher);
    }

    @ApiOperation("修改讲师功能")
    @PutMapping("/updateTeacher/{id}")
    public ResultVo updateTeacher(@PathVariable String id,@RequestBody Teacher teacher){

        Teacher result = teacherService.getById(id);
        BeanUtils.copyProperties(teacher,result);
        result.setId(id);
        boolean updateResult = teacherService.updateById(result);

        if(updateResult){
            return ResultVo.ok();
        }else {
            return ResultVo.error();
        }
    }

    /**
     * 添加讲师的方法
     * @param teacher
     * @return ResultVo
     */
    @ApiOperation("添加讲师")
    @PostMapping("addTeacher")
    public ResultVo addTeacher(@RequestBody Teacher teacher){

        boolean save = teacherService.save(teacher);
        if(save){
            return ResultVo.ok();
        }
        return ResultVo.error();
    }
}

