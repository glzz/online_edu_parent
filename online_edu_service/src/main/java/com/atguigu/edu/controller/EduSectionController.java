package com.atguigu.edu.controller;


import com.atguigu.edu.entity.EduSection;
import com.atguigu.edu.response.RetVal;
import com.atguigu.edu.service.EduSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程小节 前端控制器
 * </p>
 *
 * @author 了飘尘
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/edu/section")
@CrossOrigin
public class EduSectionController {
    @Autowired
    private EduSectionService sectionService;
    //1.添加小节
    @PostMapping
    public RetVal addSection(EduSection section){
        sectionService.addSection(section);
        return RetVal.success();
    }
    // 2.删除小节
    @DeleteMapping("{id}")
    public RetVal deleteSection(@PathVariable String id){
        sectionService.deleteSection(id);
        return RetVal.success();
    }
    //3.更新小节
    @PutMapping
    public RetVal updateSection(EduSection section){
        sectionService.updateById(section);
        return RetVal.success();
    }
    //4.根据id查询小节
    @GetMapping("{id}")
    public RetVal getSectionById(@PathVariable String id){
        EduSection section = sectionService.getById(id);
        return RetVal.success().data("section",section);
    }

}

