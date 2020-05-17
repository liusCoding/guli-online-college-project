package com.liuscoding.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liuscoding.edu.entity.Teacher;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-02
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * C端讲师分页查询
     * @param teacherPage 分页对象
     * @return 分页结果集
     */
    Page<Teacher> getTeacherFrontList(Page<Teacher> teacherPage);
}
