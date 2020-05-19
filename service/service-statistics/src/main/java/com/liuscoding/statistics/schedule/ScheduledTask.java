package com.liuscoding.statistics.schedule;

import com.liuscoding.statistics.service.StatisticsDailyService;
import com.liuscoding.statistics.utils.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @className: Scheule
 * @description: 定时任务
 * @author: liusCoding
 * @create: 2020-05-19 09:12
 */

@Component
public class ScheduledTask {

    private final  StatisticsDailyService statisticsDailyService;

    public ScheduledTask(StatisticsDailyService statisticsDailyService) {
        this.statisticsDailyService = statisticsDailyService;
    }

    /**
     * 每隔5秒执行一次这个方法
     */
    // @Scheduled(cron = "0/5 * * * * ?")
    public void task(){
        System.out.println("task执行");
    }

    /**
     * 在每天凌晨一点，把前一天的数据进行数据查询添加
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void registerCountTask(){
        statisticsDailyService.countRegister(DateUtil.formatDate(DateUtil.addDays(new Date(),-1)));
    }
}
