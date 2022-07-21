package com.atguigu.edu.service;

import com.atguigu.edu.entity.EduSubject;
import com.atguigu.edu.response.SubjectVO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 了飘尘
 * @since 2022-04-20
 */
public interface EduSubjectService extends IService<EduSubject> {

    void uploadSubject(MultipartFile file) throws Exception;

    //    判断分类是否存在 title 和parentId
    EduSubject  existSubject(String title, String parentId);

    List<SubjectVO> getAllSubject();

    boolean deleteSubjectById(String id);

    boolean saveParentSubject(EduSubject subject);

    boolean saveChildSubject(EduSubject subject);
}
