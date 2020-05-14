package com.liuscoding.edu.service;

import com.liuscoding.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liuscoding.edu.model.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-10
 */
public interface ChapterService extends IService<Chapter> {

    /**
     * 根据课程ID 查询课程的章节小节
     * @param courseId 课程id
     * @return  章节小节
     */
    List<ChapterVo> getChapterVideoByCourseId(String courseId);


    /**
     * 根据id删除章节
     * @param chapterId
     */
    void deleteChapter(String chapterId);

    /**
     * 根据课程id 删除章节
     * @param courseId 课程id
     */
    void deleteChapterByCourseId(String courseId);
}
