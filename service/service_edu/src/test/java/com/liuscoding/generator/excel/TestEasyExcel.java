package com.liuscoding.generator.excel;

import com.alibaba.excel.EasyExcel;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * @className: TestEesyExcel
 * @description: 测试Excel
 * @author: liusCoding
 * @create: 2020-05-10 10:27
 */

public class TestEasyExcel {

    @Test
    public void write(){

        /**
         * 实现Excel写的操作
         * 1.设置写入文件夹地址和excel文件地址
         */

        String fileName = "F:/write.xlsx";

        /**
         * 2.调用esayExcel里面的方法实现写操作
         *
         * write 方法两个参数，第一个参数文件路径名称，第二个参数实体类class
         */

        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(data());
    }


    @Test
    public void read(){
        String fileName = "F:/write.xlsx";

        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    private static List<DemoData> data(){
        List<DemoData> list = Lists.newArrayList();

        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();

            data.setSno(i);
            data.setSname("Tom" + i);
            list.add(data);
        }

        return list;
    }
}
