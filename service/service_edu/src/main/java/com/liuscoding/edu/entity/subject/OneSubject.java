package com.liuscoding.edu.entity.subject;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @className: OneSubject
 * @description: 一级分类
 * @author: liusCoding
 * @create: 2020-05-10 15:03
 */

@Data
public class OneSubject {

    private String id;

    private String title;

    /**
     * 一个一级分类里面有多个二级分类
     */
    List<TwoSubject> children = Lists.newArrayList();
}
