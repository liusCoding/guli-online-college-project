package com.liuscoding.edu.model.vo;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @className: ChapterVo
 * @description: 章节Vo
 * @author: liusCoding
 * @create: 2020-05-13 10:41
 */

@Data
@AllArgsConstructor
public class ChapterVo {

    private String id;

    private String title;


    /**
     * 小节
     */
    private List<VideoVo> children = Lists.newArrayList();

    public ChapterVo(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
