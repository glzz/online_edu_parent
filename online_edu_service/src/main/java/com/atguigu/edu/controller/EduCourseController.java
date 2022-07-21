package com.atguigu.edu.controller;


import com.atguigu.edu.entity.EduCourse;
import com.atguigu.edu.service.EduCourseService;
import com.atguigu.request.CourseCondition;
import com.atguigu.request.CourseInfoVO;
import com.atguigu.response.CourseConfirmVO;
import com.atguigu.response.RetVal;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 了飘尘
 * @since 2022-05-06
 */
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    //1.保存课程
    @PostMapping("saveCourseInfo")
    public RetVal saveCourseInfo(CourseInfoVO courseInfoVO){
        courseService.saveCourseInfo(courseInfoVO);
        return RetVal.success();
    }

    // 2.查询课程信息带分页带条件

    @GetMapping("queryCoursePageByCondition/{pageNum}/{pageSize}")
    public RetVal queryCoursePageByCondition(
            @PathVariable("pageNum") long pageNum,
            @PathVariable("pageSize") long pageSize,
           CourseCondition courseCondition ) {
        Page<EduCourse> coursePage = new Page<>(pageNum, pageSize);
        courseService.queryCoursePageByCondition(coursePage, courseCondition);
        long total = coursePage.getTotal();//总记录时
        List<EduCourse> courseList = coursePage.getRecords();//所有的课程的(list)集合
        return RetVal.success().data("total", total).data("courseList", courseList);
    }
    // 3.根据课程id查询课程信息
    @GetMapping("{id}")
    public RetVal getCourseById(@PathVariable String id){
        CourseInfoVO  courseInfoVO = courseService.getCourseById(id);
        return RetVal.success().data("courseInfoVO",courseInfoVO);
    }


    //4.修改课程信息
    @PutMapping("updateCourseInfo")
    public RetVal updateCourseInfo(CourseInfoVO courseInfoVO){
        courseService.updateCourseInfo(courseInfoVO);
        return RetVal.success();
    }


    // 5.课程发布确认
    @GetMapping("queryCourseConfirmInfo/{courseId}")
    public RetVal queryCourseConfirmInfo(@ApiParam(name = "courseId", value = "课程id", required = true)  @PathVariable String courseId){

        CourseConfirmVO courseConfirmVO = courseService.queryCourseConfirmInfo(courseId);
        return RetVal.success().data("courseConfirmVO",courseConfirmVO);
    }

   // 6.课程发布
    @PutMapping("publishCourse/{courseId}")
    public RetVal publishCourse(@ApiParam(name = "courseId", value = "课程id", required = true)  @PathVariable String courseId){
        EduCourse course = new EduCourse();
        course.setId(courseId);
        course.setStatus(" Draft");
        courseService.updateById(course);
        return RetVal.success();
    }
    // 7.删除视频
    //删除课程信息
    @DeleteMapping("{courseId}")
    public RetVal deleteCourseById(@ApiParam(name = "courseId", value = "课程id", required = true) @PathVariable String courseId){
        boolean flag =   courseService.deleteCourseById(courseId);
        return RetVal.success();
    }



}

