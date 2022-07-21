package com.atguigu.edu.controller.front;

import com.atguigu.edu.entity.EduCourse;
import com.atguigu.edu.entity.EduTeacher;
import com.atguigu.edu.response.RetVal;
import com.atguigu.edu.service.EduCourseService;
import com.atguigu.edu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: 了飘尘
 * @date: 2022/7/3 20:38
 * @version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/edu/front/teacher")
@CrossOrigin

public class FrontTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;


    //1.讲师查询带分页
    @GetMapping("queryTeacherPage/{pageNum}/{pageSize}")
    public RetVal queryTeacherPage(@PathVariable("pageNum") long pageNum,
                                   @PathVariable("pageSize") long pageSize) {

        Page<EduTeacher> teacherPage = new Page<>(pageNum, pageSize);

        Map<String, Object> retMap = teacherService.queryTeacherPage(teacherPage);

        return RetVal.success().data(retMap);
    }

    //2.根据id查询讲师详情
    @GetMapping("queryTeacherDetailById/{teacherId}")
    public RetVal queryTeacherDetailById(@PathVariable("teacherId") String teacherId) {

        //a.讲师基本信息
        EduTeacher teacher = teacherService.getById(teacherId);
        //b.所授课程信息
        LambdaQueryWrapper<EduCourse> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduCourse::getTeacherId, teacherId);
        List<EduCourse> courseList = courseService.list(wrapper);


        return RetVal.success().data("teacher",teacher).data("courseList",courseList);
    }
}