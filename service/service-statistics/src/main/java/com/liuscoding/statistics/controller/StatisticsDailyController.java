package com.liuscoding.statistics.controller;


import com.liuscoding.commonutils.vo.ResultVo;
import com.liuscoding.statistics.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-19
 */
@RestController
@RequestMapping("/statistics")
@CrossOrigin
@Api(tags = "网站统计分析")
public class StatisticsDailyController {
    private final StatisticsDailyService statisticsDailyService;

    public StatisticsDailyController(StatisticsDailyService statisticsDailyService) {
        this.statisticsDailyService = statisticsDailyService;
    }


    @ApiOperation("统计指定日期的注册人数，生成统计数据")
    @GetMapping("/countRegister/{day}")
    public ResultVo countRegister(@PathVariable String day){
        statisticsDailyService.countRegister(day);
        return ResultVo.ok();
    }

    @ApiOperation("查询图表显示数据")
    @GetMapping("/showData/{type}/{begin}/{end}")
    public ResultVo showData(@PathVariable String type,@PathVariable String begin,
                             @PathVariable String end){
       Map<String,Object> resultMap = statisticsDailyService.showData(type,begin,end);

       return ResultVo.ok().data(resultMap);
    }
}

