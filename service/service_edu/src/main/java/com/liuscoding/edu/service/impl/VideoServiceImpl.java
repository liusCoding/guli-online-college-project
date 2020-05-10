package com.liuscoding.edu.service.impl;

import com.liuscoding.edu.entity.Video;
import com.liuscoding.edu.mapper.VideoMapper;
import com.liuscoding.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-10
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
