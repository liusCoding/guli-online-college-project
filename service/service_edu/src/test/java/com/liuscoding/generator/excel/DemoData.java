package com.liuscoding.generator.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @className: DemoData
 * @description: Excel测试数据
 * @author: liusCoding
 * @create: 2020-05-10 10:24
 */

@Data
public class DemoData {


    /**
     * @ExcelProperty   设置Excel表头名称
     *
     * value 表头名称
     *
     * index  第几列
     */
    @ExcelProperty(value = "学生编码",index = 0)
    private Integer sno;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
