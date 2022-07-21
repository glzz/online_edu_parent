package com.atguigu.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.edu.entity.EduSubject;
import com.atguigu.edu.entity.ExcelSubject;
import com.atguigu.edu.listener.SubjectListener;
import com.atguigu.edu.mapper.EduSubjectMapper;
import com.atguigu.edu.service.EduSubjectService;
import com.atguigu.exception.EduException;
import com.atguigu.response.SubjectVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 了飘尘
 * @since 2022-04-20
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Autowired
    private SubjectListener subjectListener;
    @Autowired
    private EduSubjectMapper subjectMapper;

    //拿到excel文件，对文进行读取操作，然后写数据读取到数据库
    @Override
    public void uploadSubject(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        //思考：2.你中有我，我中有你会报错吗？
        EasyExcel.read(inputStream, ExcelSubject.class, subjectListener).doReadAll();
    }

    /**
     * @param title
     * @param parentId
     * @return
     */

//    判断分类是否存在 title 和parentId
    @Override
    public EduSubject existSubject(String title, String parentId) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", title);
        wrapper.eq("parent_id", parentId);
        EduSubject existSubject = baseMapper.selectOne(wrapper);
        return existSubject;
    }

    @Override
    public List<SubjectVO> getAllSubject() {
        //a.设计一个返回给前端的对象
        //b.查询所有的课程分类
        List<EduSubject> subjectList = baseMapper.selectList(null);
        //c.查询所有的一级分类（找组长）
        List<SubjectVO> parentSubjectVos = new ArrayList<>();

        for (EduSubject subject : subjectList) {
            //判断标准parentId=0
            if (subject.getParentId().equals("0")) {
                SubjectVO subjectVO = new SubjectVO();
                BeanUtils.copyProperties(subject, subjectVO);
                parentSubjectVos.add(subjectVO);
            }
        }
        // d.把所有找到一级分类放到一个角落(map)
        Map<String, SubjectVO> parentSubjectMap = new HashMap<>();

        for (SubjectVO parentSubjectListVo : parentSubjectVos) {
            // key 	一级分类id  , value	一级分类对象
            parentSubjectMap.put(parentSubjectListVo.getId(), parentSubjectListVo);
        }
        // e.查询二级分类(找组员的过程)
        for (EduSubject subject : subjectList) {
            // 判断标准 parentId!=0
            if (!subject.getParentId().equals("0")) {
                // 拿到二级分类的parentId(组号) 从map中找到该二级分类的一级分类
                SubjectVO parentSubjectVo = parentSubjectMap.get(subject.getParentId());
                // 得到一级分类的children 把二级分类塞进去 成为它的子节点
                SubjectVO childSubjectVO = new SubjectVO();
                childSubjectVO.setId(subject.getId());
                childSubjectVO.setTitle(subject.getTitle());
                BeanUtils.copyProperties(subject, childSubjectVO);
                parentSubjectVo.getChildren().add(childSubjectVO);
            }
        }
        // f.返回所有的一级分类
        return parentSubjectVos;
    }

    //3.根据节点id删除节点
    @Override
    public boolean deleteSubjectById(String id) {
        // 需要判断该节点是一级分类还是二级分类
        //看该节点下面是否有子节点cccc
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Integer count = subjectMapper.selectCount(wrapper);
        //如果count==0代表该节点码云子节点
        if (count == 0) {
            int rows = subjectMapper.deleteById(id);
            return rows > 0;
        } else {
            throw new EduException(20001, "该节点存在子节点！");
        }
    }

    //4.添加一级分类
    @Override
    public boolean saveParentSubject(EduSubject subject) {
        EduSubject parentSubject = existSubject(subject.getTitle(), "0");
        if (parentSubject == null) {
            parentSubject = new EduSubject();
            parentSubject.setParentId("0");
            parentSubject.setTitle(subject.getTitle());
            int rows = baseMapper.insert(parentSubject);

            return rows > 0;
        }
        return false;
    }

    //5.添加二级分类
    @Override
    public boolean saveChildSubject(EduSubject childSubject) {
        EduSubject existSubject = existSubject(childSubject.getTitle(), childSubject.getParentId());
        if (existSubject == null) {
            int rows = baseMapper.insert(childSubject);
            return rows > 0;
        }
        return false;
    }

}
