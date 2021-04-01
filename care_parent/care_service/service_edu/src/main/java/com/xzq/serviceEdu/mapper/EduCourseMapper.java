package com.xzq.serviceEdu.mapper;

import com.xzq.serviceEdu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzq.serviceEdu.entity.frontVo.CourseWebVo;
import com.xzq.serviceEdu.entity.publish.CoursePublishVo;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-01-28
 */
@Repository
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**
     * @Description: 根据ID获取课程发布信息
     * @Author xuzhiqiang
     * @Date 2021/3/1 14:25
     */
    CoursePublishVo getCoursePublishVoById(String id);

    /**
     * @Description: 根据课程ID查询课程信息
     * @Author xuzhiqiang
     * @Date 2021/4/1 15:49
     */
    CourseWebVo selectInfoWebById(String courseId);
}
