package com.liuscoding.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.liuscoding.statistics.client.MemberClient;
import com.liuscoding.statistics.entity.StatisticsDaily;
import com.liuscoding.statistics.mapper.StatisticsDailyMapper;
import com.liuscoding.statistics.service.StatisticsDailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-19
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    private final MemberClient memberClient;

    public StatisticsDailyServiceImpl(MemberClient memberClient) {
        this.memberClient = memberClient;
    }

    /**
     * 统计指定日期的注册人数，生成统计数据
     *
     * @param day 指定日期
     */
    @Override
    public void countRegister(String day) {
        //添加记录之前删除表相同的日期的数据

        LambdaQueryWrapper<StatisticsDaily> deleteWrapper = new LambdaQueryWrapper<>();
        deleteWrapper.eq(StatisticsDaily::getDateCalculated,day);
        this.remove(deleteWrapper);

        //远程调用得到某一天的注册人数
        Integer countRegister = memberClient.countRegister(day);

        //把获取的数据添加到数据库，统计分析里面
        StatisticsDaily statisticsDaily = StatisticsDaily.builder()
                //统计日期
                .dateCalculated(day)
                //注册人数
                .registerNum(countRegister)
                .videoViewNum(RandomUtils.nextInt(100, 200))
                .loginNum(RandomUtils.nextInt(100, 200))
                .courseNum(RandomUtils.nextInt(100, 200))
                .build();
        this.save(statisticsDaily);
    }

    /**
     * 图表
     *
     * @param type  显示数据的类型
     * @param begin 开始时间
     * @param end   结束时间
     * @return 查询的结果
     */
    @Override
    public Map<String, Object> showData(String type, String begin, String end) {

        //根据条件查询对应的数据
        LambdaQueryWrapper<StatisticsDaily> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.between(StatisticsDaily::getDateCalculated,begin,end);

        List<StatisticsDaily> dataList = this.list(queryWrapper);

        //因为返回的有两部分数据： 日期 和 日期对应的数据
        //前端要求数组json数组，对应后端的是list集合
        //构建两个list集合
        List<String> dateList = Lists.newArrayList();
        List<Integer> numDataList = Lists.newArrayList();
        dataList.forEach( data ->{
            dateList.add(data.getDateCalculated());
            //封装对应数量
            switch (type) {
                case "login_num":
                    numDataList.add(data.getLoginNum());
                    break;
                case "register_num":
                    numDataList.add(data.getRegisterNum());
                    break;
                case "video_view_num":
                    numDataList.add(data.getVideoViewNum());
                    break;
                case "course_num":
                    numDataList.add(data.getCourseNum());
                    break;
                default:
                    break;
            }
        });

        HashMap<String, Object> map = Maps.newHashMap();
        map.put("dateList",dateList);
        map.put("numList",numDataList);
        return map;
    }
}
