package com.atguigu.edu.service;

import com.atguigu.edu.entity.EduSection;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程小节 服务类
 * </p>
 *
 * @author 了飘尘
 * @since 2022-05-16
 */
public interface EduSectionService extends IService<EduSection> {

    void addSection(EduSection section);

    void deleteSection(String id);

    void deleteSectionByCourseId(String courseId);
}
