package com.liuscoding.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.liuscoding.edu.entity.Course;
import com.liuscoding.edu.entity.CourseDescription;
import com.liuscoding.edu.entity.Subject;
import com.liuscoding.edu.enums.EduResultCode;
import com.liuscoding.edu.mapper.CourseMapper;
import com.liuscoding.edu.model.form.CourseInfoForm;
import com.liuscoding.edu.model.query.CourseQuery;
import com.liuscoding.edu.model.vo.CourseInfoVo;
import com.liuscoding.edu.model.vo.CoursePublishVo;
import com.liuscoding.edu.model.vo.CourseWebVo;
import com.liuscoding.edu.service.*;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-10
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    private final CourseDescriptionService courseDescriptionService;
    private final SubjectService subjectService;
    private final VideoService videoService;
    private final ChapterService chapterService;

    public CourseServiceImpl(CourseDescriptionService courseDescriptionService, SubjectService subjectService, VideoService videoService, ChapterService chapterService) {
        this.courseDescriptionService = courseDescriptionService;
        this.subjectService = subjectService;
        this.videoService = videoService;
        this.chapterService = chapterService;
    }

    /**
     * 保存课程信息
     *
     * @param courseInfoForm 课程信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //1.向课程表添加课程基本信息
        Subject subject = subjectService.getById(courseInfoForm.getSubjectId());
        subject = Optional.ofNullable(subject).orElseThrow(() -> GuliException.from(EduResultCode.DATA_NO_EXIST));

        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        course.setSubjectParentId(subject.getParentId());
        boolean saveResult = this.save(course);
        if(!saveResult){
            //添加失败，提示用户
            throw GuliException.from(EduResultCode.SAVE_ERROR);
        }

        //获取添加之后的课程id
        String courseId = course.getId();

        //2.向课程简介表添加简介
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(courseId);
        boolean saveCourseDescResult = courseDescriptionService.save(courseDescription);
        if (!saveCourseDescResult){
            throw GuliException.from(EduResultCode.SAVE_ERROR);
        }


        return courseId;
    }

    /**
     * 根据课程id 查询课程信息
     *
     * @param courseId 课程id
     * @return CourseInfoVo
     */
    @Override
    public CourseInfoVo getCourseInfoByCourseId(String courseId) {

        //1.查询课程信息
        Course course = this.getById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();

        BeanUtils.copyProperties(course,courseInfoVo);

        //2.查询课程描述信息
        CourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());
        return courseInfoVo;
    }

    /**
     * 修改课程信息
     *
     * @param courseInfoForm
     */
    @Override
    public void updateCourse(CourseInfoForm courseInfoForm) {
        //1.修改课程表
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        boolean updateResult = updateById(course);
        if(!updateResult){
            throw GuliException.from(EduResultCode.UPDATE_ERROR);
        }
        
        //2.修改描述表
        CourseDescription courseDescription = new CourseDescription(courseInfoForm.getId(),courseInfoForm.getDescription());
        boolean updateDescResult = courseDescriptionService.updateById(courseDescription);

        if (!updateDescResult){
            throw GuliException.from(EduResultCode.UPDATE_ERROR);
        }

    }

    /**
     * 根据课程id查询课程确认信息
     *
     * @param id 课程id
     * @return CoursePublishVo
     */
    @Override
    public CoursePublishVo publishCourseInfo(String id) {

        CoursePublishVo publishCourseInfo = this.baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    /**
     * 根据课程id删除课程信息
     *
     * @param courseId 课程id
     */
    @Override
    public void deleteCourse(String courseId) {
        //1.根据课程id删除小节
        videoService.deleteVideoByCourseId(courseId);

        //2.根据课程id删除章节
        chapterService.deleteChapterByCourseId(courseId);

        //3.根据课程id删除描述
        boolean removeDesc = courseDescriptionService.removeById(courseId);
        if(!removeDesc){
            throw GuliException.from(EduResultCode.DELETE_ERROR);
        }

        //4.根据课程id删除课程本身
        boolean removeCourseResult = this.removeById(courseId);

        if(!removeCourseResult){
            throw GuliException.from(EduResultCode.DELETE_ERROR);
        }

    }

    /**
     * 条件查询带分页查询课程
     *
     * @param coursePage  分页对象
     * @param courseQuery 条件查询
     * @return 查询结果
     */
    @Override
    public Map<String, Object> getCourseFrontList(Page<Course> coursePage, CourseQuery courseQuery) {

        LambdaQueryWrapper<Course> courseWrapper = new LambdaQueryWrapper<>();
        //若一级分类不为空
        courseWrapper.eq(StringUtils.isNotBlank(courseQuery.getSubjectParentId()),Course::getSubjectParentId,courseQuery.getSubjectParentId());
        //若二级分类不为空
        courseWrapper.eq(StringUtils.isNotBlank(courseQuery.getSubjectId()),Course::getSubjectId,courseQuery.getSubjectId());

        //关注度不为空
        courseWrapper.orderByDesc(StringUtils.isNotBlank(courseQuery.getBuyCountSort()),Course::getBuyCount);

        //最新不为空
        courseWrapper.orderByDesc(StringUtils.isNotBlank(courseQuery.getGmtCreateSort()),Course::getGmtCreate);

        //价格不为空
        courseWrapper.orderByDesc(StringUtils.isNotBlank(courseQuery.getPriceSort()),Course::getPrice);

        this.page(coursePage, courseWrapper);

        List<Course> records = coursePage.getRecords();
        long current = coursePage.getCurrent();
        long pages = coursePage.getPages();
        long size = coursePage.getSize();
        long total = coursePage.getTotal();
        //下一页
        boolean hasNext = coursePage.hasNext();
        //上一页
        boolean hasPrevious = coursePage.hasPrevious();

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = Maps.newHashMap();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    /**
     * 查询课程基本信息
     *
     * @param courseId
     * @return
     */
    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return this.baseMapper.getBaseCourseInfo(courseId);
    }
}
