package com.atguigu.edu.service;

import com.atguigu.edu.entity.EduTeacher;
import com.atguigu.request.TeacherConditionVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author 了飘尘
 * @since 2022-03-27
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void queryTeacherPageByCondition(Page<EduTeacher> teacherPage, TeacherConditionVO teacherConditionVO);


    Map<String, Object> queryTeacherPage(Page<EduTeacher> teacherPage);
}
