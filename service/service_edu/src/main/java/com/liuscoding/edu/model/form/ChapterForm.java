package com.liuscoding.edu.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: ChapterForm
 * @description:
 * @author: liusCoding
 * @create: 2020-05-13 15:42
 */

@Data
public class ChapterForm implements Serializable {


    @ApiModelProperty(value = "章节ID")
    private String id;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;
}
