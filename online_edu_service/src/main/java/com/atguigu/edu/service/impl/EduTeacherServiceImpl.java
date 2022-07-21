package com.atguigu.edu.service.impl;

import com.atguigu.edu.entity.EduTeacher;
import com.atguigu.edu.mapper.EduTeacherMapper;
import com.atguigu.edu.service.EduTeacherService;
import com.atguigu.request.TeacherConditionVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author 了飘尘
 * @since 2022-03-27
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void queryTeacherPageByCondition(Page<EduTeacher> teacherPage, TeacherConditionVO teacherConditionVO) {
        //LambdaQueryWrapper 的使用
        LambdaQueryWrapper<EduTeacher> wrapper = new LambdaQueryWrapper<>();
        String name = teacherConditionVO.getName();
        Integer level = teacherConditionVO.getLevel();
        String beginTime = teacherConditionVO.getBeginTime();
        String endTime = teacherConditionVO.getEndTime();
        //封装查询条件
        if (StringUtils.isNotEmpty(name)) {
            wrapper.like(EduTeacher::getName, name);
        }
        if (level != null) {
            wrapper.eq(EduTeacher::getLevel, level);
        }
        if (StringUtils.isNotEmpty(beginTime)) {
            wrapper.ge(EduTeacher::getGmtCreate, beginTime);
        }
        if (StringUtils.isNotEmpty(endTime)) {
            wrapper.le(EduTeacher::getGmtModified, beginTime);
        }
        wrapper.orderByDesc(EduTeacher::getGmtCreate);
        baseMapper.selectPage(teacherPage, wrapper);

    }

    @Override
    public Map<String, Object> queryTeacherPage(Page<EduTeacher> teacherPage) {

        baseMapper.selectPage(teacherPage, null);

        List<EduTeacher> teacherList = teacherPage.getRecords();
        boolean hasNext = teacherPage.hasNext();//是否有下一页
        boolean hasPrevious = teacherPage.hasPrevious();//是否有上一页
        long currentPage = teacherPage.getCurrent();
        long pages = teacherPage.getPages();
        long total = teacherPage.getTotal();
        long size = teacherPage.getSize();

        Map<String, Object> retMap = new HashMap<>();
        retMap.put("teacherList", teacherList);
        retMap.put("pages", pages);
        retMap.put("total", total);
        retMap.put("currentPage", currentPage);
        retMap.put("size", size);
        retMap.put("hasNext", hasNext);
        retMap.put("hasPrevious", hasPrevious);
        return retMap;
    }


}
