package com.liuscoding.edu.client;

import com.liuscoding.commonutils.vo.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @className: VodClient
 * @description:
 * @author: liusCoding
 * @create: 2020-05-15 07:10
 */

@FeignClient(value = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    /**
     * 定义方法调用的路径  根据视频id删除阿里云视频
     * @PathVariable 注解一定要指定参数名称，否则会出错
     * @param id 视频id
     * @return ResultVo
     */
    @DeleteMapping("/eduvod/video/removeAliyunVideo/{id}")
     ResultVo removeAliyunVideo(@PathVariable("id") String id);


    /**
     * 定义调用多个视频的方法
     * @param videoIdList  多个视频id
     * @return ResultVo
     */
    @DeleteMapping("/eduvod/video/deleteBatch")
     ResultVo deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
