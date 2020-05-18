package com.liuscoding.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuscoding.edu.entity.Chapter;
import com.liuscoding.edu.entity.Video;
import com.liuscoding.edu.enums.EduResultCode;
import com.liuscoding.edu.mapper.ChapterMapper;
import com.liuscoding.edu.model.vo.ChapterVo;
import com.liuscoding.edu.model.vo.VideoVo;
import com.liuscoding.edu.service.ChapterService;
import com.liuscoding.edu.service.VideoService;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-10
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    private final VideoService videoService;

    public ChapterServiceImpl(VideoService videoService) {
        this.videoService = videoService;
    }

    /**
     * 根据课程ID 查询课程的章节小节
     *
     * @param courseId 课程id
     * @return 章节小节
     */
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        //1.根据课程id 查询课程里面所有的章节
        LambdaQueryWrapper<Chapter> chapterQuery = new LambdaQueryWrapper<>();
        chapterQuery.eq(Chapter::getCourseId,courseId);
        List<Chapter> chapterList = this.list(chapterQuery);

        //封装小节
        List<ChapterVo> chapterVoList = chapterList.stream()
                .map(chapter -> new ChapterVo(chapter.getId(), chapter.getTitle()))
                .collect(toList());


        //2.根据课程id查询课程里面所有的小节
        LambdaQueryWrapper<Video> videoQuery = new LambdaQueryWrapper();
        videoQuery.eq(Video::getCourseId,courseId);
        List<Video> videoList = videoService.list(videoQuery);
        //封装小节
        chapterVoList.forEach(chapterVo -> {

            List<VideoVo> videoVoList = videoList.stream()
                    .filter(video -> video.getChapterId().equals(chapterVo.getId()))
                    .map(video -> new VideoVo(video.getId(), video.getTitle(),video.getVideoSourceId()))
                    .collect(toList());

            chapterVo.setChildren(videoVoList);
        });

        return chapterVoList;
    }

    /**
     * 根据id删除章节
     *
     * @param chapterId
     */
    @Override
    public void deleteChapter(String chapterId) {
        // 根据chapterId 查询小节表 ，如果查询有数据，不进行删除。
        LambdaQueryWrapper<Video> chapterQuery = new LambdaQueryWrapper<>();
        chapterQuery.eq(Video::getChapterId,chapterId);
        int count = videoService.count(chapterQuery);
        if (count>0){
            throw GuliException.from(EduResultCode.DELETE_ERROR);
        }
        boolean result = removeById(chapterId);
        if(!result){
            throw GuliException.from(EduResultCode.DELETE_ERROR);
        }
    }

    /**
     * 根据课程id 删除章节
     *
     * @param courseId 课程id
     */
    @Override
    public void deleteChapterByCourseId(String courseId) {
        LambdaQueryWrapper<Chapter> chapterWrapper = new LambdaQueryWrapper<>();
        chapterWrapper.eq(Chapter::getCourseId,courseId);
        boolean removeResult = this.remove(chapterWrapper);
        if(!removeResult){
            throw  GuliException.from(EduResultCode.DELETE_ERROR);
        }
    }
}
