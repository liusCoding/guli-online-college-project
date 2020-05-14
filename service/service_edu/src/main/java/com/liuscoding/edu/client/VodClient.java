package com.liuscoding.edu.client;

import com.liuscoding.commonutils.vo.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @className: VodClient
 * @description:
 * @author: liusCoding
 * @create: 2020-05-15 07:10
 */

@FeignClient("service-vod")
@Component
public interface VodClient {

    @DeleteMapping("/eduvod/video/removeAliyunVideo/{id}")
     ResultVo removeAliyunVideo(@PathVariable("id") String id);
}
