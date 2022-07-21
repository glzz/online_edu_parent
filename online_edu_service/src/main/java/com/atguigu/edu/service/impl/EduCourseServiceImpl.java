package com.atguigu.edu.service.impl;

import com.atguigu.edu.entity.EduCourse;
import com.atguigu.edu.entity.EduCourseDescription;
import com.atguigu.edu.mapper.EduCourseMapper;
import com.atguigu.edu.service.EduChapterService;
import com.atguigu.edu.service.EduCourseDescriptionService;
import com.atguigu.edu.service.EduCourseService;
import com.atguigu.edu.service.EduSectionService;
import com.atguigu.request.CourseCondition;
import com.atguigu.request.CourseInfoVO;
import com.atguigu.response.CourseConfirmVO;
import com.atguigu.response.CourseDetailInfoVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 了飘尘
 * @since 2022-05-06
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;
    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private EduSectionService sectionService;
    @Autowired
    private EduCourseMapper courseMapper;


    @Override
    public void saveCourseInfo(CourseInfoVO courseInfoVO) {
        //1.保存课程基本信息
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVO, course);
        baseMapper.insert(course);
        //2.保存课程详细信息 二张表共用一个主键
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseInfoVO.getDescription());
        courseDescriptionService.save(courseDescription);


    }

    /**
     * 查询课程信息带分页带条件
     *
     * @param coursePage
     * @param courseCondition
     */
    @Override
    public void queryCoursePageByCondition(Page<EduCourse> coursePage, CourseCondition courseCondition) {

        //LambdaQueryWrapper 的使用
        LambdaQueryWrapper<EduCourse> wrapper = new LambdaQueryWrapper<>();
        String status = courseCondition.getStatus();
        String title = courseCondition.getTitle();
        //封装查询条件
        if (StringUtils.isNotEmpty(status)) {
            wrapper.ge(EduCourse::getStatus, status);
        }

        if (StringUtils.isNotEmpty(title)) {
            wrapper.like(EduCourse::getTitle, title);
        }
        wrapper.orderByDesc(EduCourse::getGmtCreate);
        baseMapper.selectPage(coursePage, wrapper);
    }

    @Override
    public CourseInfoVO getCourseById(String id) {
        CourseInfoVO courseInfoVO = new CourseInfoVO();
        EduCourse course = baseMapper.selectById(id);
        BeanUtils.copyProperties(course, courseInfoVO);
        EduCourseDescription description = courseDescriptionService.getById(id);
        if (description != null) {
            courseInfoVO.setDescription(description.getDescription());
        }
        return courseInfoVO;
    }

    //修改课程信息
    @Override
    public void updateCourseInfo(CourseInfoVO courseInfoVO) {
        //1.保存课程基本信息
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVO, course);
        baseMapper.updateById(course);
        //2.保存课程详细信息 二张表共用一个主键
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseInfoVO.getDescription());
        courseDescriptionService.updateById(courseDescription);
    }


    @Override
    public CourseConfirmVO queryCourseConfirmInfo(String courseId) {
        return baseMapper.queryCourseConfirmInfo(courseId);

    }

    //根据id删除课程

    @Override
    public boolean deleteCourseById(String courseId) {
        // a.该课程所对应的章节
        chapterService.deleteChapterByCourseId(courseId);
        // b.该课程所对应的小节
        sectionService.deleteSectionByCourseId(courseId);
        // c.该课程所对应的描述信息
        // d.该课程所对应的基本信息
        // e.整个操作过程应该保证事务完整性
        int rows = courseMapper.deleteById(courseId);
        courseDescriptionService.removeById(courseId);
        return rows > 0;
    }

    @Override
    public CourseDetailInfoVO queryCourseDetailById(String courseId) {
        return baseMapper.queryCourseDetailById(courseId);

    }
}
