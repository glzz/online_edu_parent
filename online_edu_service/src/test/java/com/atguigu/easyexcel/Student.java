package com.atguigu.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author: 了飘尘
 * @date: 2022/4/20 20:49
 * @version: 1.0
 * @Description:
 */

@Data
public class Student {
    @ExcelProperty(value = "学生编号", index = 1)
    private  Integer stuNo;
    @ExcelProperty(value = "学生姓名", index = 0)
    private  String stuName;

}
