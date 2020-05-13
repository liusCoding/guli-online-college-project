package com.liuscoding.edu.controller;


import com.liuscoding.commonutils.vo.ResultVo;
import com.liuscoding.edu.model.vo.ChapterVo;
import com.liuscoding.edu.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@CrossOrigin
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
}

