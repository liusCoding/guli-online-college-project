package com.liuscoding.generator.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;

import java.util.Map;

/**
 * @className: ExcelListener
 * @description: Excel读取数据监听器
 * @author: liusCoding
 * @create: 2020-05-10 10:41
 */

public class ExcelListener extends AnalysisEventListener<DemoData> {

    /**
     * 一行一行读取excel的内容
     * @param demoData
     * @param analysisContext
     */
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("**** "  +  demoData);
    }

    /**
     * 读取完成之后
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    /**
     * 读取表头内容
     * @param headMap
     * @param context
     */
    public void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context) {
        System.out.println("表头：" + headMap);
    }

}
