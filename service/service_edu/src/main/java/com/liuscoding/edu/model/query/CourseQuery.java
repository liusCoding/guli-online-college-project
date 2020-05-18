package com.liuscoding.edu.model.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className: CourseQuery
 * @description: 课程查询对象
 * @author: liusCoding
 * @create: 2020-05-17 11:25
 */

@Data
public class CourseQuery {

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "一级类别id")
    private String subjectParentId;

    @ApiModelProperty(value = "二级类别id")
    private String subjectId;

    @ApiModelProperty(value = "销量排序")
    private String buyCountSort;

    @ApiModelProperty(value = "最新时间排序")
    private String gmtCreateSort;

    @ApiModelProperty(value = "价格排序")
    private String priceSort;
}
