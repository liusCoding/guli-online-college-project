package com.liuscoding.order.client;

import com.liuscoding.commonutils.model.vo.CourseOrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-edu",fallback = CourseClientImpl.class)
@Component
public interface CourseClient {

    /**
     * 根据课程id 查询课程信息
     * @param id  课程id
     * @return CourseOrderVo 课程基本信息
     */
    @GetMapping("/edu/courseFront/getCourseInfoOrder/{id}")
    CourseOrderVo getCourseInfoOrder(@PathVariable("id") String id);

}
