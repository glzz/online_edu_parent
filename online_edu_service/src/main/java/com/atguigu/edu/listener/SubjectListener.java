package com.atguigu.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.edu.entity.EduSubject;
import com.atguigu.edu.entity.ExcelSubject;
import com.atguigu.edu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: 了飘尘
 * @date: 2022/4/20 21:49
 * @version: 1.0
 * @Description:
 */
@Component
public class SubjectListener extends AnalysisEventListener<ExcelSubject> {

    @Autowired
    private EduSubjectService subjectService;
    //一行行读取数据信息，将数据写入到数据库
    @Override
    public void invoke(ExcelSubject excelSubject, AnalysisContext analysisContext) {
        String parentSubjectName = excelSubject.getParentSubjectName();
        //保存一级分类的时候，需要判断该分类在数据库中是否存在 如果不存在 parentId为0
        EduSubject parentSubject = subjectService.existSubject(parentSubjectName, "0");
        if (parentSubject == null) {
            parentSubject= new EduSubject();
            parentSubject.setParentId("0");
            parentSubject.setTitle(parentSubjectName);
            subjectService.save(parentSubject);
        }
        //保存二级分类的时候，需要判断该分类在数据库中是否存在 如果不存在 parentId为对应的一级分类的id
        String childSubjectName = excelSubject.getChildSubjectName();
        //思考：1.能拿到id吗？
        String parentSubjectId = parentSubject.getId();
        EduSubject childSubject = subjectService.existSubject(childSubjectName, parentSubjectId);
        if (childSubject == null) {
            childSubject= new EduSubject();
            childSubject.setParentId(parentSubjectId);
            childSubject.setTitle(childSubjectName);
            subjectService.save(childSubject);
        }
    }
    //读取成功后的操作
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("数据操作成功！------------------");
    }
}
