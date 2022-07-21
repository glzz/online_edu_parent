package com.atguigu.edu.mapper;

import com.atguigu.edu.entity.EduCourse;
import com.atguigu.edu.response.CourseConfirmVO;
import com.atguigu.edu.response.CourseDetailInfoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author 了飘尘
 * @since 2022-05-06
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {


    CourseConfirmVO queryCourseConfirmInfo(String courseId);

    CourseDetailInfoVO queryCourseDetailById(String courseId);
}


