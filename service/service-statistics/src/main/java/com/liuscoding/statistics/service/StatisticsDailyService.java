package com.liuscoding.statistics.service;

import com.liuscoding.statistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-19
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    /**
     * 统计指定日期的注册人数，生成统计数据
     * @param day 指定日期
     */
    void countRegister(String day);

    /**
     *  图表
     * @param type 显示数据的类型
     * @param begin 开始时间
     * @param end 结束时间
     * @return 查询的结果
     */
    Map<String, Object> showData(String type, String begin, String end);
}
