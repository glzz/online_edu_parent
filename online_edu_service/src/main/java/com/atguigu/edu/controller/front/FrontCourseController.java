package com.atguigu.edu.controller.front;

import com.atguigu.edu.response.ChapterVO;
import com.atguigu.edu.response.CourseDetailInfoVO;
import com.atguigu.edu.response.RetVal;
import com.atguigu.edu.service.EduChapterService;
import com.atguigu.edu.service.EduCourseService;
import com.atguigu.edu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: 了飘尘
 * @date: 2022/7/3 20:38
 * @version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/edu/front/course")
@CrossOrigin

public class FrontCourseController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private EduTeacherService teacherService;


    //1.根据id查询课程信息
    @GetMapping("queryCourseDetailById/{courseId}")
    public RetVal queryCourseDetailById(@PathVariable  String courseId) {
        //a.课程详情信息
        CourseDetailInfoVO courseDetailInfoVO =  courseService.queryCourseDetailById(courseId);
        //b.章节小节信息
        List<ChapterVO> chapterSAndSection=chapterService.getChapterAndSection(courseId);
        return RetVal.success().data("chapterSAndSection",chapterSAndSection).data("courseDetailInfoVO",courseDetailInfoVO);
    }

}