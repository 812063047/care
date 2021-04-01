package com.xzq.serviceEdu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzq.serviceEdu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzq.serviceEdu.entity.EduTeacher;
import com.xzq.serviceEdu.entity.frontVo.CourseFrontVo;
import com.xzq.serviceEdu.entity.frontVo.CourseWebVo;
import com.xzq.serviceEdu.entity.publish.CoursePublishVo;
import com.xzq.serviceEdu.entity.vo.CourseInfoVo;
import com.xzq.serviceEdu.query.EduCourseQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-01-28
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * @Description: 添加课程信息
     * @Author xuzhiqiang
     * @Date 2021/1/28 17:24
     */
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * @Description: 根据ID查找课程信息
     * @Author xuzhiqiang
     * @Date 2021/2/5 13:38
     */
    CourseInfoVo getCourseInfoVoById(String id);

    /**
     * @Description: 修改课程表和描述表
     * @Author xuzhiqiang
     * @Date 2021/2/5 15:29
     */
    String updateCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * @Description: 根据ID获取课程发布信息
     * @Author xuzhiqiang
     * @Date 2021/3/1 14:24
     */
    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String courseId);

    /**
     * @Description: 分页查询
     * @Author xuzhiqiang
     * @Date 2021/3/1 16:23
     * @return
     */
    IPage<EduCourse> pageQueryCourse(Long current, Long limit, EduCourseQuery eduCourseQuery);

    /**
     * @Description: 删除课程信息
     * @Author xuzhiqiang
     * @Date 2021/3/1 18:04
     */
    boolean deleteByCourseId(String courseId);

    /**
     * @Description: 批量删除课程
     * @Author xuzhiqiang
     * @Date 2021/3/4 13:20
     */
    boolean deleteBatch(String courseIds);

    /**
     * @Description: 获取前8个课程
     * @Author xuzhiqiang
     * @Date 2021/3/8 16:58
     */

    List<EduCourse> listCourse();
    /**
     * @Description:根据讲师ID查询
     * @Author xuzhiqiang
     * @Date 2021/4/1 13:36
     */
    List<EduCourse> selectByTeacherId(String id);
    /**
     * @Description: 根据条件分页查询
     * @Author xuzhiqiang
     * @Date 2021/4/1 14:34
     */
    Map<String, Object> pageListWeb(Page<EduCourse> pageParam, CourseFrontVo courseFrontVo);

    /**
     * @Description: 根据课程id查询课程基本信息
     * @Author xuzhiqiang
     * @Date 2021/4/1 15:45
     */
    CourseWebVo selectInfoWebById(String courseId);
}
