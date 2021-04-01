package com.xzq.serviceEdu.service;

import com.xzq.serviceEdu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzq.serviceEdu.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-01-28
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * @Description: 根据课程ID查询章节
     * @Author xuzhiqiang
     * @Date 2021/2/5 14:07
     */
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    /**
     * @Description: 根据章节ID删除章节
     * @Author xuzhiqiang
     * @Date 2021/2/5 17:23
     */
    boolean removeChapterById(String id);

    /**
     * @Description: 根据课程ID删除章节
     * @Author xuzhiqiang
     * @Date 2021/3/1 18:11
     */
    boolean deleteByCourseId(String courseId);
}
