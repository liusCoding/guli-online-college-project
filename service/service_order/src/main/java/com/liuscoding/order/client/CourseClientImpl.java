package com.liuscoding.order.client;

import com.liuscoding.commonutils.model.vo.CourseOrderVo;
import com.liuscoding.commonutils.result.ResultCode;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import org.springframework.stereotype.Component;

/**
 * @className: CourseClientImpl
 * @description:
 * @author: liusCoding
 * @create: 2020-05-18 13:45
 */
@Component
public class CourseClientImpl implements CourseClient {
    /**
     * 根据课程id 查询课程信息
     *
     * @param id 课程id
     * @return CourseOrderVo 课程基本信息
     */
    @Override
    public CourseOrderVo getCourseInfoOrder(String id) {
       throw GuliException.from(ResultCode.REMOTE_CALL_COURSE);
    }
}
