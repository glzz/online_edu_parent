package com.atguigu.easyexcel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author: 了飘尘
 * @date: 2022/4/20 20:38
 * @version: 1.0
 * @Description:
 */
public class EasyExcelTest {
    //1.往excel中写数据
    @Test
    public void testWriteExcel() {
        //a.向哪个excel文件中写数据
        String filePath= "F:\\one.xlsx";
        //b.写什么样的数据
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Student student = new Student();
            student.setStuNo(i);
            student.setStuName("老王" + i);
            students.add(student);
        }
        //c.往哪个sheet写数据  //write(写入文件名，写入对象类型)
        EasyExcel.write(filePath, Student.class).sheet("学生列表").doWrite(students);
    }
    @Test
    public  void  testReadExcel(){
        //a.从哪个excel文件中读数据
        String filePath= "F:\\one.xlsx";
    //b.读取数据后到如何操作
        EasyExcel.read(filePath, Student.class, new ReadStudentListener()).doReadAll();

    }

}
