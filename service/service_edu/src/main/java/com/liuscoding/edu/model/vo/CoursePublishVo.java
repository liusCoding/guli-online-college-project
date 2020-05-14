package com.liuscoding.edu.model.vo;

import lombok.Data;

/**
 * @className: CoursePublishVo
 * @description:
 * @author: liusCoding
 * @create: 2020-05-13 20:28
 */
@Data
public class CoursePublishVo {

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;
}
