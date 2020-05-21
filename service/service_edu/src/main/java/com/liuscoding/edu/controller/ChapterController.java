package com.liuscoding.edu.controller;


import com.liuscoding.commonutils.vo.ResultVo;
import com.liuscoding.edu.entity.Chapter;
import com.liuscoding.edu.enums.EduResultCode;
import com.liuscoding.edu.model.form.ChapterForm;
import com.liuscoding.edu.model.vo.ChapterVo;
import com.liuscoding.edu.service.ChapterService;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-10
 */
@RestController
@RequestMapping("/edu/chapter")
@Api(tags = "课程章节管理")
//@CrossOrigin
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }


    @ApiOperation("根据id查询课程大纲列表")
    @GetMapping("/getChapterVideo/{courseId}")
    public ResultVo getChapterVideo(@PathVariable String courseId){

        List<ChapterVo> chapterVoList =  chapterService.getChapterVideoByCourseId(courseId);
        return ResultVo.ok().data("allChapterVideo",chapterVoList);
    }

    @ApiOperation("添加章节")
    @PostMapping("/addChapter")
    public ResultVo addChapter(@RequestBody ChapterForm chapterForm){
        Chapter chapter = new Chapter();
        BeanUtils.copyProperties(chapterForm,chapter);
        boolean saveResult = chapterService.save(chapter);
        if(!saveResult){
            throw GuliException.from(EduResultCode.SAVE_ERROR);
        }
        return ResultVo.ok();
    }

    @ApiOperation("根据id查询章节")
    @GetMapping("getChapterInfo/{chapterId}")
    public ResultVo getChapterInfo(@PathVariable String chapterId){
        Chapter chapter = chapterService.getById(chapterId);
        return ResultVo.ok().data("chapter",chapter);
    }

    @ApiOperation("修改章节")
    @PutMapping("/updateChapter")
    public ResultVo updateChapter(@RequestBody ChapterForm chapterForm){
        Chapter chapter = new Chapter();
        BeanUtils.copyProperties(chapterForm,chapter);
        boolean updateResult = chapterService.updateById(chapter);
        if (!updateResult){
            throw GuliException.from(EduResultCode.UPDATE_ERROR);
        }
        return ResultVo.ok();

    }

    @ApiOperation("根据id删除章节")
    @DeleteMapping("/{chapterId}")
    public ResultVo deleteChapter(@PathVariable String chapterId){
        chapterService.deleteChapter(chapterId);
        return ResultVo.ok();
    }

}

