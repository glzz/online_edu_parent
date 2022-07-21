package com.atguigu.edu.entity;

import lombok.Data;

/**
 * @author: 了飘尘
 * @date: 2022/4/20 21:44
 * @version: 1.0
 * @Description:
 */
@Data
public class ExcelSubject {
    private  String parentSubjectName; //一级分类
    private  String childSubjectName;//二级分类


}
