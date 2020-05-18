package com.liuscoding.edu.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @className: VideoVo
 * @description: 小节vo
 * @author: liusCoding
 * @create: 2020-05-13 10:42
 */

@Data
@AllArgsConstructor
public class VideoVo {


    private  String id ;

    private String title;

    private String videoSourceId;

}
