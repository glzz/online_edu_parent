package com.atguigu.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author: 了飘尘
 * @date: 2022/4/20 21:14
 * @version: 1.0
 * @Description:
 */
public class ReadStudentListener extends AnalysisEventListener<Student> {
    //invoke 一行一行的读取数据
    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println(student);

    }

    //doAfterAllAnalysed 读取数据完成后的操作
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("数据读取完成！！！");
    }
    //读取表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息" + headMap);
    }
}
