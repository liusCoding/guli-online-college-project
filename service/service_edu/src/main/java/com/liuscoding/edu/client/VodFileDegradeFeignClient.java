package com.liuscoding.edu.client;

import com.liuscoding.commonutils.vo.ResultVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @className: VodFileDegradeFeignClient
 * @description:
 * @author: liusCoding
 * @create: 2020-05-15 09:34
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {

    //出错之后会执行
    /**
     * 定义方法调用的路径  根据视频id删除阿里云视频
     *
     * @param id 视频id
     * @return ResultVo
     * @PathVariable 注解一定要指定参数名称，否则会出错
     */
    @Override
    public ResultVo removeAliyunVideo(String id) {
        return ResultVo.error().message("删除视频出错了");
    }

    /**
     * 定义调用多个视频的方法
     *
     * @param videoIdList 多个视频id
     * @return ResultVo
     */
    @Override
    public ResultVo deleteBatch(List<String> videoIdList) {
        return ResultVo.error().message("删除多个视频出错了");
    }
}
