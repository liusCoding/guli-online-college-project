package com.liuscoding.edu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuscoding
 *
 * 课程状态
 */

@AllArgsConstructor
@Getter
public enum CourseStatusEnum {

    DRAFT("Draft"),

    NORMAL("Normal");


    private String desc;



}
