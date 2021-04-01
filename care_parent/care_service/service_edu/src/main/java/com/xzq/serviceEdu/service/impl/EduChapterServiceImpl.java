package com.xzq.serviceEdu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzq.serviceBase.Exception.CareException;
import com.xzq.serviceEdu.entity.EduChapter;
import com.xzq.serviceEdu.entity.EduVideo;
import com.xzq.serviceEdu.entity.chapter.ChapterVo;
import com.xzq.serviceEdu.entity.chapter.VideoVo;
import com.xzq.serviceEdu.mapper.EduChapterMapper;
import com.xzq.serviceEdu.mapper.EduVideoMapper;
import com.xzq.serviceEdu.service.EduChapterService;
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
 * @author testjava
 * @since 2021-01-28
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduChapterMapper eduChapterMapper;

    @Autowired
    private EduVideoMapper eduVideoMapper;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //根据课程id获取章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapters = eduChapterMapper.selectList(wrapperChapter);
        //根据课程ID获取小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> eduVideos = eduVideoMapper.selectList(wrapperVideo);
        //遍历章节
        List<ChapterVo> chapterVoList = new ArrayList<>();
        for (int i = 0; i < eduChapters.size(); i++) {
            EduChapter eduChapter = eduChapters.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            //遍历章节的小节
            List<VideoVo> videoVoList = new ArrayList<>();
            for (int j = 0; j < eduVideos.size(); j++) {
                EduVideo eduVideo = eduVideos.get(j);
                //判断对应关系
                if(eduChapter.getId().equals(eduVideo.getChapterId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
            chapterVoList.add(chapterVo);
        }
        return chapterVoList;
    }

    @Override
    public boolean removeChapterById(String id) {
        //根据章节ID查找小章节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("chapter_id",id);
        Integer num = eduVideoMapper.selectCount(wrapperVideo);
        if(num!=0){
            throw  new CareException(20001,"请先删除章节里面的小节视频");
        }
        Integer i = eduChapterMapper.deleteById(id);
        return null != i && i > 0;

    }

    @Override
    public boolean deleteByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        Integer result = eduChapterMapper.delete(queryWrapper);
        return null != result && result > 0;
    }
}
