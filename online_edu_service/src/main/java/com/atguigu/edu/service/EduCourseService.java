package com.atguigu.edu.service;

import com.atguigu.edu.entity.EduCourse;
import com.atguigu.request.CourseCondition;
import com.atguigu.request.CourseInfoVO;
import com.atguigu.response.CourseConfirmVO;
import com.atguigu.response.CourseDetailInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 了飘尘
 * @since 2022-05-06
 */
public interface EduCourseService extends IService<EduCourse> {

    void saveCourseInfo(CourseInfoVO courseInfoVO);

    void queryCoursePageByCondition(Page<EduCourse> coursePage, CourseCondition courseCondition);

    CourseInfoVO getCourseById(String id);

    void updateCourseInfo(CourseInfoVO courseInfoVO);

    //根据id删除课程信息
    boolean deleteCourseById(String courseId);

    CourseConfirmVO queryCourseConfirmInfo(String courseId);

    CourseDetailInfoVO queryCourseDetailById(String courseId);
}
