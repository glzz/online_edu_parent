package com.atguigu.edu.controller.front;

import com.atguigu.edu.entity.EduBanner;
import com.atguigu.edu.entity.EduCourse;
import com.atguigu.edu.entity.EduTeacher;
import com.atguigu.edu.response.RetVal;
import com.atguigu.edu.service.EduBannerService;
import com.atguigu.edu.service.EduCourseService;
import com.atguigu.edu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: 了飘尘
 * @date: 2022/7/3 20:38
 * @version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/edu/front/")
@CrossOrigin

public class FrontIndexController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduBannerService bannerService;

    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private RedisTemplate redisTemplate;




    // //1.前台查询banner的方法
    // @GetMapping("getAllBanner")
    // public RetVal getAllBanner() {
    //     //使用缓存的步骤
    //     StringRedisSerializer KeySerializer = new StringRedisSerializer();
    //     redisTemplate.setKeySerializer(KeySerializer);
    //     List<EduBanner> bannerList= (List<EduBanner>) redisTemplate.opsForValue().get("indexInfo::banner");
    //     if (bannerList == null) {
    //         bannerList = bannerService.list(null);
    //         redisTemplate.opsForValue().set("indexInfo::banner",bannerList);
    //     }
    //     return RetVal.success().data("bannerList",bannerList);
    // }

    //1.前台查询banner的方法
    @Cacheable(value = "indexInfo", key = "'banner'")
    @GetMapping("getAllBanner")
    public RetVal getAllBanner() {
        List<EduBanner> bannerList = bannerService.list(null);
        return RetVal.success().data("bannerList",bannerList);
    }


    //2.查询前8条热门课程，查询前4条名师
    @Cacheable(value = "indexInfo", key = "'teacherCourse'")
    @GetMapping("queryTeacherAndCourse")
    public RetVal queryTeacherAndCourse() {
        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("view_count");
        wrapper.last("limit 8");
        List<EduCourse> courseList = courseService.list(wrapper);

        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("sort");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);

        return RetVal.success().data("courseList",courseList).data("teacherList",teacherList);
    }

}