package com.atguigu.edu.controller;

import com.atguigu.edu.entity.EduTeacher;
import com.atguigu.edu.service.EduTeacherService;
import com.atguigu.request.TeacherConditionVO;
import com.atguigu.response.RetVal;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 了飘尘
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/edu/teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    //7.修改讲师
    @PutMapping
    public RetVal updateTeacher(EduTeacher teacher) {
        boolean flag = teacherService.updateById(teacher);
        if (flag) {
            return RetVal.success();
        } else {
            return RetVal.error();
        }
    }


    //6.根据id查询讲师
    @GetMapping("{id}")
    public  RetVal queryTeacherById(@PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        return RetVal.success().data("teacher",teacher);
    }

    //5.添加讲师
    @PostMapping
    public RetVal saveTeacher(EduTeacher teacher) {

        boolean flag = teacherService.save(teacher);
        if (flag) {
            return RetVal.success();
        } else {
            return RetVal.error();
        }
    }

    //4.讲师查询带分页带查询条件
    @GetMapping("queryTeacherPageByCondition/{pageNum}/{pageSize}")
    public RetVal queryTeacherPageByCondition(
            @PathVariable("pageNum") long pageNum,
            @PathVariable("pageSize") long pageSize,
            TeacherConditionVO teacherConditionVO) {
        Page<EduTeacher> teacherPage = new Page<>(pageNum, pageSize);
        teacherService.queryTeacherPageByCondition(teacherPage, teacherConditionVO);
        long total = teacherPage.getTotal();//总记录时
        List<EduTeacher> teacherList = teacherPage.getRecords();//所有的讲师的(list)集合
        return RetVal.success().data("total", total).data("teacherList", teacherList);
    }


    //3.讲师查询带分页
    @GetMapping("queryTeacherPage/{pageNum}/{pageSize}")
    public RetVal queryTeacherPage(@PathVariable("pageNum") long pageNum,
                                   @PathVariable("pageSize")long pageSize) {

        Page<EduTeacher> teacherPage = new Page<>(pageNum, pageSize);

        teacherService.page(teacherPage, null);//二个参数，①分页参数（页码,每页条数），②分页条件（例如查询名字带高的用户，然后分页，名字带高就是分页条件），此单纯分页，不带条件
        long total = teacherPage.getTotal();//总记录时
        List<EduTeacher> teacherList = teacherPage.getRecords();//所有的讲师的(list)集合
        return RetVal.success().data("total",total).data("teacherList",teacherList);
    }

    //2.删除讲师信息
    @DeleteMapping("{id}")
    public RetVal deleteTeacherById(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return RetVal.success();
        } else {
            return RetVal.error();
        }
    }

    //1.查询所有的讲师
    @GetMapping
    public RetVal getAllTeacher() {
        List<EduTeacher> teacherList = teacherService.list(null);
        for (EduTeacher teacher : teacherList) {
            System.out.println(teacher);
        }
        return RetVal.success().data("teacherList", teacherList);
    }
}

