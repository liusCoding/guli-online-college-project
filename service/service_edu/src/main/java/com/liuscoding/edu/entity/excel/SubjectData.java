package com.liuscoding.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @className: SubjectData
 * @description: 分类
 * @author: liusCoding
 * @create: 2020-05-10 11:08
 */
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;


    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
