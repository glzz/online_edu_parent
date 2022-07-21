package com.atguigu.edu.service.impl;

import com.atguigu.edu.entity.EduChapter;
import com.atguigu.edu.entity.EduSection;
import com.atguigu.edu.mapper.EduChapterMapper;
import com.atguigu.edu.service.EduChapterService;
import com.atguigu.edu.service.EduSectionService;
import com.atguigu.exception.EduException;
import com.atguigu.response.ChapterVO;
import com.atguigu.response.SectionVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 了飘尘
 * @since 2022-05-16
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduSectionService sectionService;

    @Override
    public boolean saveChapter(EduChapter chapter) {
        //判断是否存在
        EduChapter existChapter = existChapter(chapter.getCourseId(), chapter.getTitle());
        if(existChapter==null){
            int insert = baseMapper.insert(chapter);
            return insert>0;
        }else{
            throw new EduException(20001,"章节已经重复");
        }
    }

    public EduChapter existChapter(String courseId, String chapterName) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("title", chapterName);
        EduChapter chapter = baseMapper.selectOne(queryWrapper);
        return chapter;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        //判断是否有小节
        QueryWrapper<EduSection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id",chapterId);
        int count = sectionService.count(queryWrapper);
        //没有小节
        if(count==0){
            int i = baseMapper.deleteById(chapterId);
            return i>0;
        }else{
            throw new EduException(20001,"该章节存在小节");
        }
    }


    @Override
    public List<ChapterVO> getChapterAndSection(String courseId) {
        //a.查询所有的章节
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id",courseId);
        List<EduChapter> chapterList = baseMapper.selectList(chapterWrapper);
        //b.查询所有的小节
        QueryWrapper<EduSection> sectionWrapper = new QueryWrapper<>();
        sectionWrapper.eq("course_id",courseId);
        List<EduSection> sectionList = sectionService.list(sectionWrapper);
        //c.把小节封装到章节当中
        ArrayList<ChapterVO> chapterVOList = new ArrayList<>();
        for (EduChapter chapter : chapterList) {
            ChapterVO chapterVO = new ChapterVO();
            BeanUtils.copyProperties(chapter,chapterVO);
            for (EduSection section : sectionList) {
                //判断标准section里面的章节id==章节里面的id
                if(section.getChapterId().equals(chapter.getId())){
                    SectionVO sectionVO = new SectionVO();
                    BeanUtils.copyProperties(section,sectionVO);
                    //把小节信息塞到chapter的children属性
                    chapterVO.getChildren().add(sectionVO);
                }
            }
            chapterVOList.add(chapterVO);
        }
        return chapterVOList;
    }

    @Override
    public void deleteChapterByCourseId(String courseId) {
        LambdaQueryWrapper<EduChapter> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduChapter::getCourseId, courseId);
        baseMapper.delete(wrapper);

    }
}
