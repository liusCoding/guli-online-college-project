package com.liuscoding.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuscoding.edu.entity.Teacher;
import com.liuscoding.edu.mapper.TeacherMapper;
import com.liuscoding.edu.service.TeacherService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-02
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    /**
     * C端讲师分页查询
     *
     * @param teacherPage 分页对象
     * @return 分页结果集
     */
    @Override
    public Page<Teacher> getTeacherFrontList(Page<Teacher> teacherPage) {

        LambdaQueryWrapper<Teacher> teacherQuery = new LambdaQueryWrapper<>();
        teacherQuery.orderByDesc(Teacher::getId);

        teacherPage = (Page<Teacher>) this.page(teacherPage,teacherQuery);
        return teacherPage;
    }
}
